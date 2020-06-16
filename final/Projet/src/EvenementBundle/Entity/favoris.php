<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * favoris
 *
 * @ORM\Table(name="favoris")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\favorisRepository")
 */
class favoris
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var \UserBundle\Entity\user
     *
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\user")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_Membre", referencedColumnName="id" ,onDelete="CASCADE")
     * })
     */
    private $idMembre;

    /**
     * @var \EvenementBundle\Entity\evenement
     *
     * @ORM\ManyToOne(targetEntity="EvenementBundle\Entity\evenement")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_event", referencedColumnName="id" ,onDelete="CASCADE")
     * })
     */
    private $idEvent;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idMembre
     *
     * @param integer $idMembre
     *
     * @return favoris
     */
    public function setIdMembre($idMembre)
    {
        $this->idMembre = $idMembre;

        return $this;
    }

    /**
     * Get idMembre
     *
     * @return int
     */
    public function getIdMembre()
    {
        return $this->idMembre;
    }

    /**
     * Set idEvent
     *
     * @param integer $idEvent
     *
     * @return favoris
     */
    public function setIdEvent($idEvent)
    {
        $this->idEvent = $idEvent;

        return $this;
    }

    /**
     * Get idEvent
     *
     * @return int
     */
    public function getIdEvent()
    {
        return $this->idEvent;
    }
}

