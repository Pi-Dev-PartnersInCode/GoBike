<?php

namespace EvenementBundle\Controller;
use EvenementBundle\Entity\evenement;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


class evenementController extends Controller
{

    public function AddevenementAction( \Symfony\Component\HttpFoundation\Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenement = new evenement();
        $form = $this->createForm('EvenementBundle\Form\evenementType', $evenement);
        $form->handleRequest($request);
        $now=new \DateTime('now');
        if ($form->isSubmitted() && $now<=$evenement->getDateevent()){
            $em->persist($evenement);
            $em->flush();




            return $this->redirectToRoute('evenement_Affiche');
        }

        return $this->render('EvenementBundle:evenement:ajoutevenement.html.twig', array(
            'evenement' => $evenement,
            'form' => $form->createView(),

        ));
    }
    public function ListevenementAction(Request $request)
    {


        $m = $this->getDoctrine()->getManager();
        $evenement = $m->getRepository("EvenementBundle:evenement")->findAll();
        $paginator=$this->get('knp_paginator');
        $result=$paginator->paginate($evenement,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5));

        return $this->render('EvenementBundle:evenement:Afficheevenement.html.twig', array(
            'evenement' => $result
        ));
    }
    public function SupprimeAction($id)
    {

        $em = $this->getDoctrine()->getManager();

        $evenement = $em->getRepository("EvenementBundle:evenement")->find($id);
        $em->remove($evenement);
        $em->flush();
        $basic  = new \Nexmo\Client\Credentials\Basic('4bb343f3', 'LqfcVMdquWf6jU8a');
        $client = new \Nexmo\Client($basic);

        $message = $client->message()->send([
            'to' => '21696681004',
            'from' => 'Velo.tn',
            'text' => 'Event deleted',
        ]);


        return $this->redirectToRoute('evenement_Affiche');
    }
    public function editevenementAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $evenement = $em->getRepository('EvenementBundle:evenement')->find($id);
        $editForm = $this->createForm('EvenementBundle\Form\evenementType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $em->persist($evenement);
            $em->flush();

            return $this->redirectToRoute('evenement_Affiche');
        }
        $em = $this->getDoctrine()->getManager();

        return $this->render('EvenementBundle:evenement:editevenement.html.twig', array(
            'evenement' => $evenement,
            'form' => $editForm->createView(),
        ));
    }


    public function ListeventFrontAction(Request $request)
    {
        $evenement=$this->getDoctrine()->getRepository(evenement::class)->findAll();
        if ($request->isMethod('POST'))
        {    $repository=$this->getDoctrine()->getManager()->getRepository(evenement::class);
            if ($request->get('rechercher'))
            {
                $type=$request->get('type');
                $lieu=$request->get('lieu');

                $evenement=$repository->rechercher($type,$lieu);
                return $this->render('EvenementBundle:evenement:AfficheFrontevenement.html.twig', array('evenement' => $evenement));
            }

        }
        return $this->render('EvenementBundle:evenement:AfficheFrontevenement.html.twig',array('evenement'=> $evenement));
    }
    public function calendrierAction()
    {
        return $this->render('EvenementBundle:evenement:Affichecalendrier.html.twig');
    }

}
