<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include '../libs/medoo.php';
// Using Medoo namespace
use Medoo\Medoo;
// Initialize
$database = new Medoo([
    'database_type' => 'mysql',
    'database_name' => 'tintuc',
    'server' => 'localhost',
    'username' => 'admin',
    'password' => 'admin',
    'charset' => 'utf8'
]);

function get_posts($page = 0) {
    global $database;
    $total_post_in_page = 10;
    $posts = $database->select("post", "*", [
        "LIMIT" => [($page * $total_post_in_page) - $total_post_in_page, $total_post_in_page],
        "ORDER" => ["post_id" => "DESC"]
    ]);
    return $posts;
}

function get_total_pages() {
    global $database;
    $total_post_in_page = 10;
    $count = $database->count('post');
    $total_page = $count / $total_post_in_page;
    return ceil($total_page);
}

function delete_post($post_id) {
    global $database;
    $result = $database->delete('post', [
        'post_id' => $post_id
    ]);
    return $result;# true or false
}

function save_post($post_title, $post_desc, $post_thumb, $category_id, $post_content) {
    global $database;
    $r = $database->insert("post", [
        "post_title" => $post_title,
        "post_desc" => $post_desc,
        "post_thumb" => $post_thumb,
        "category_id" => $category_id,
        "post_content" => $post_content
    ]);
    return $r;
}

function get_post_by_id($post_id) {
    global $database;
    $post = $database->get("post", '*', ['post_id' => $post_id]);
    return $post;
}

function update_post($post_id, $post_title, $post_desc, $post_thumb, $category_id, $post_content){
    global $database;
    $r = $database->update("post", [
        "post_title" => $post_title,
        "post_desc" => $post_desc,
        "post_thumb" => $post_thumb,
        "category_id" => $category_id,
        "post_content" => $post_content
    ], ['post_id' => $post_id]);
    return $r;
}

function get_post_by_category_id($category_id, $offset, $limit) {
    global $database;
    $posts = $database->select("post", [
        'post_id', 'post_title', 'post_desc', 'post_thumb', 'category_id'
    ], [
        'category_id' => $category_id,
        "LIMIT" => [$offset, $limit],
        "ORDER" => ["post_id" => "DESC"]
    ]);
    return $posts;
}