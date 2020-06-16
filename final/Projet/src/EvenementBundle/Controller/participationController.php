<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\participation;
use EvenementBundle\Entity\evenement;
use UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use mikehaertl\wkhtmlto\Pdf;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class participationController extends Controller
{
    public function ListparticipationAction()
    {


        $m = $this->getDoctrine()->getManager();
        $participation = $m->getRepository("EvenementBundle:participation")->findAll();


        return $this->render('EvenementBundle:participation:Afficheparticipation.html.twig', array(
            'participation' => $participation
        ));
    }
    public function supprimeparticipationAction($id)
    {

        $em = $this->getDoctrine()->getManager();

        $participation = $em->getRepository("EvenementBundle:participation")->find($id);
        $em->remove($participation);
        $em->flush();

        return $this->redirectToRoute('participation_Affiche');
    }
    public function editparticipationAction(Request $request, $id)
    {
        $em = $this->getDoctrine()->getManager();

        $participation = $em->getRepository('EvenementBundle:participation')->find($id);

           $editForm = $this->createForm('EvenementBundle\Form\participationType', $participation);

           $editForm->handleRequest($request);

           if ($editForm->isSubmitted() && $editForm->isValid()) {

               $em->persist($participation);
               $em->flush();

               return $this->redirectToRoute('participation_Affiche');
           }
           $em = $this->getDoctrine()->getManager();

        return $this->render('EvenementBundle:participation:editparticipation.html.twig', array(
            'participation' => $participation,
            'form' => $editForm->createView(),
        ));
    }
    public function AddparticipationAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $participation = new participation();
        $user = $em->getRepository(User::class)->find($this->getUser());
        $event = $em->getRepository(evenement::class)->find($id);
        $test=$em->getRepository(participation::class)->findBy(['idMembre' => $this->getUser(),'idEvent' => $id]);
        if ($test) {
            return $this->redirectToRoute('evenement_Front_Affiche');
        }
        else{
            $participation->setIdEvent($event);
            $participation->setIdMembre($user);
            $participation->setRanking(0);
            $participation->setRecord("00:00:00");


            $em->persist($participation);
            $em->flush();

            return $this->redirectToRoute('participation_Front_Affiche');
        }
        return $this->redirectToRoute('evenement_Front_Affiche');


    }
    public function ListparticipationFrontAction()
    {


        $em = $this->getDoctrine()->getManager();

        $participation = $em->getRepository("EvenementBundle:participation")->findBy(['idMembre' => $this->getUser()]) ;

        return $this->render('EvenementBundle:participation:AfficheFrontparticipation.html.twig', array(
            'participation' => $participation
        ));
    }
    public function supprimeparticipationfrontAction($id)
    {

        $em = $this->getDoctrine()->getManager();

        $participation = $em->getRepository("EvenementBundle:participation")->find($id);
        $em->remove($participation);
        $em->flush();

        return $this->redirectToRoute('participation_Front_Affiche');
    }
    public function ListrecordAction()
    {


        $m = $this->getDoctrine()->getManager();
        $participation = $m->getRepository("EvenementBundle:participation")->findAll();


        return $this->render('EvenementBundle:participation:Afficherecord.html.twig', array(
            'participation' => $participation
        ));
    }
    public function pdfAction(Request $request, $id)

    {$em = $this->getDoctrine()->getManager();

        $participant = $em->getRepository('EvenementBundle:participation')->findBy(['idEvent' => $id]);
        $snappy = $this->get('knp_snappy.pdf');

        $html = $this->renderView('EvenementBundle:evenement:pdf.html.twig', array(
            'f' => $participant,
            "title" => "participation"
        ));
        $filename = 'custom_pdf_from_twig';
        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type' => 'application/pdf',
                'Content-Disposition' => 'inline; filename="' . $filename . '.pdf"'
            )
        );
    }

    public function  find_userAction($username)
    {
        $em = $this->getDoctrine()->getManager();
        $sql = "select * from user where username=:dom";
        $params[':dom'] = $username;
        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute($params);
        $a = $stmt->fetchAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($a);
        return new JsonResponse($formatted);
    }
    public function  find_eventAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $sql = "select * from evenement";
        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute();
        $a = $stmt->fetchAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($a);
        return new JsonResponse($formatted);
    }

    public function  mob_participerAction(Request $request,$id,$id_u)
    {
        $em = $this->getDoctrine()->getManager();
        $sql = "INSERT INTO participation(id_event, id_Membre) VALUES (:dom1,:dom2)";
        $params[':dom1'] = $id;
        $params[':dom2'] = $id_u;

        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute($params);

        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize('ok');

       // $basic  = new \Nexmo\Client\Credentials\Basic('4bb343f3', 'LqfcVMdquWf6jU8a');
      //  $client = new \Nexmo\Client($basic);

       // $message = $client->message()->send([
      //      'to' => '21696681004',
      //      'from' => 'GoBike',
       //     'text' => 'New Participation',
       // ]);
        return new JsonResponse($formatted);
    }
    public function  my_eventsAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $sql = "SELECT * from evenement INNER join participation on evenement.id=participation.id_event where participation.id_Membre=:dom";
        $params[':dom'] = $id;
        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute($params);
        $a = $stmt->fetchAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($a);
        return new JsonResponse($formatted);
    }

    public function  checkParticipationMobAction(Request $request,$id,$idu)
    {
        $em = $this->getDoctrine()->getManager();
        $sql = "SELECT * from participation where id_Membre=:dom and id_event =:dom1";
        $params[':dom'] = $idu;
        $params[':dom1'] = $id;
        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute($params);
        $a = $stmt->fetchAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($a);
        return new JsonResponse($formatted);
    }
    public function  suppParAction(Request $request,$id,$id_u)
    {
        $em = $this->getDoctrine()->getManager();
        $sql = "Delete from participation where id_event=:val and id_Membre=:val2";
        $params[':val'] = $id;
        $params[':val2'] = $id_u;

        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute($params);

        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize('ok');

        return new JsonResponse($formatted);
    }

}
