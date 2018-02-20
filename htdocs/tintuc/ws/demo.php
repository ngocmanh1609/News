<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$data = array();

$data['post'] = array('id' => 1, 'post_title1' => 'a');
$data['post1'] = array('id' => 2, 'post_title2' => 'a');
$data['post2'] = array('id' => 3, 'post_title3' => 'a');
$data['post3'] = array('id' => 4, 'post_title4' => 'a');

header('Content-Type: application/json');
echo json_encode($data);