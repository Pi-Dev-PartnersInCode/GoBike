<?php

namespace EvenementBundle\Repository;

/**
 * evenementRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class evenementRepository extends \Doctrine\ORM\EntityRepository
{
    public function getWhatYouWant($yarab)
    {
        $qb = $this->createQueryBuilder('u');
        $qb->where('u.id != :identifier')
            ->setParameter('identifier', $yarab);

        return $qb->getQuery()
            ->getResult();
    }
    public function rechercher($type, $lieu)
    {
        $sql="select m from EvenementBundle:evenement m Where 1=1";
        if(!empty($type))
        {
            $sql=$sql." and m.type='".$type."'";
        }
        if(!empty($lieu))
        {
            $sql=$sql." and m.lieu='".$lieu."'";
        }


        var_dump($sql);
        $qb=$this->getEntityManager()->
        createQuery($sql);


        return $query=$qb->getResult();



    }

}
