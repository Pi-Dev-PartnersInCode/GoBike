<?php

namespace EvenementBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * participation
 *
 * @ORM\Table(name="participation")
 * @ORM\Entity(repositoryClass="EvenementBundle\Repository\participationRepository")
 */
class participation
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
     * @var String
     *
     * @ORM\Column(name="record", type="string", length=255)
     */
    private $record;

    /**
     * @var int
     *
     * @ORM\Column(name="ranking", type="integer")
     */
    private $ranking;


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
     * @return participation
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
     * @return participation
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

    /**
     * Set record
     *
     * @param \DateTime $record
     *
     * @return participation
     */
    public function setRecord($record)
    {
        $this->record = $record;

        return $this;
    }

    /**
     * Get record
     *
     * @return \DateTime
     */
    public function getRecord()
    {
        return $this->record;
    }

    /**
     * Set ranking
     *
     * @param integer $ranking
     *
     * @return participation
     */
    public function setRanking($ranking)
    {
        $this->ranking = $ranking;

        return $this;
    }

    /**
     * Get ranking
     *
     * @return int
     */
    public function getRanking()
    {
        return $this->ranking;
    }

    public function __toString()
    {
        return (string) $this->getId();
    }
}

