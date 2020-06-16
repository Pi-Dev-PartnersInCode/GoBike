<?php

namespace EvenementBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class evenementType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')->add('lieu', ChoiceType::class, array('label' => 'Type ',
            'choices' => array(' Ariana' => 'Ariana',
                'Beja' => 'Beja','Ben Arous' => 'Ben Arous','Bizerte' => 'Bizerte','Gafsa' => 'Gafsa','Jendouba' => 'Jendouba','Kebili' => 'Kebili','Kef' => 'Kef','Mahdia' => 'Mahdia','Manouba' => 'Manouba','Monastir' => 'Monastir','Nabeul' => 'Nabeul','Sfax' => 'Sfax','Sousse' => 'Sousse','Tataouine' => 'Tataouine','Tozeur' => 'Tozeur','Tunis' => 'Tunis','Zaghouan' => 'Zaghouan'),
            'required' => true, 'multiple' => false,))->add('description',TextareaType::class)->add('dateevent')->add('type', ChoiceType::class, array('label' => 'Type ',
            'choices' => array(' BMX' => 'BMX',
                'Route' => 'Route','VTT' => 'VTT','CYCLO_CROSS' => 'CYCLO_CROSS'),
            'required' => true, 'multiple' => false,));
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'EvenementBundle\Entity\evenement'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'evenementbundle_evenement';
    }


}
