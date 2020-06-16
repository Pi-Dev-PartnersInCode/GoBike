<?php

namespace UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('UserBundle:Default:index.html.twig');
    }
    public function MembreAction()
    {
        return $this->render('UserBundle::Page_Membre.html.twig');
    }
    public function ResponsableAction()
    {
        return $this->render('UserBundle::Page_Responsable.html.twig');
    }
    public function AdminAction()
    {
        return $this->render('UserBundle::Page_Admin.html.twig');
    }
}
