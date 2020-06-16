<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\evenement;
use EvenementBundle\Entity\favoris;
use EvenementBundle\Entity\participation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use UserBundle\Entity\User;
use Symfony\Component\HttpFoundation\Request;

class favorisController extends Controller
{
    public function AddfavorisAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $favoris = new favoris();
        $user = $em->getRepository(User::class)->find($this->getUser());
        $event = $em->getRepository(evenement::class)->find($id);
        $test=$em->getRepository(favoris::class)->findBy(['idMembre' => $this->getUser(),'idEvent' => $id]);

        if (!$test)
        {
            $favoris->setIdEvent($event);
            $favoris->setIdMembre($user);



            $em->persist($favoris);
            $em->flush();

            return $this->redirectToRoute('favoris_Front_Affiche');
        }
        else{
            return $this->redirectToRoute('evenement_Front_Affiche');
        }

        return $this->redirectToRoute('evenement_Front_Affiche');


    }
    public function ListfavorisFrontAction()
    {


        $em = $this->getDoctrine()->getManager();

        $favoris = $em->getRepository("EvenementBundle:favoris")->findBy(['idMembre' => $this->getUser()]) ;

        return $this->render('EvenementBundle:evenement:AfficheFrontfavoris.html.twig', array(
            'favoris' => $favoris
        ));
    }
    public function supprimefavorisfrontAction($id)
    {

        $em = $this->getDoctrine()->getManager();

        $favoris = $em->getRepository("EvenementBundle:favoris")->find($id);
        $em->remove($favoris);
        $em->flush();

        return $this->redirectToRoute('favoris_Front_Affiche');
    }
}
