<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php 
session_start();
include './func.php';

if (empty($_SESSION['user_name'])){
    echo '<center>Bạn chưa đăng nhập, yêu cầu bạn đăng nhập !!!</center>';
    header("Refresh: 3; url=login.php");
    return;
}

$post_id = 0;

if (!empty($_GET['id'])) {
    $post_id = $_GET['id'];
}

$post = get_post_by_id($post_id);

$post_title = $post['post_title'];
$post_desc = $post['post_desc'];
$post_thumb = $post['post_thumb'];
$category_id = $post['category_id'];
$post_content = $post['post_content'];

if (!empty($_POST)){
    $post_title = $_POST['post_title'];
    $post_desc = $_POST['post_desc'];
    $post_thumb = $_POST['post_thumb'];
    $category_id = $_POST['category_id'];
    $post_content = $_POST['post_content'];

    $is_ok = true;

    $error = array();
    if (empty($post_title)) {
        $error['post_title'] = "Tiêu đề không được bỏ trống";
        $is_ok = false;
    }

    if (empty($post_desc)) {
        $error['post_desc'] = "Mô tả không được bỏ trống";
        $is_ok = false;
    }

    if (empty($post_thumb)) {
        $error['post_thumb'] = "Hình ảnh không được bỏ trống";
        $is_ok = false;
    }

    if (empty($post_content)) {
        $error['post_content'] = "Nội dung không được bỏ trống";
        $is_ok = false;
    }

    if (!empty($post_content) && strlen($post_content) < 200){
        $error['post_content'] = "Nội dung quá ngắn";
    }

    if ($is_ok){
        $r = update_post($post_id, $post_title, $post_desc, $post_thumb, $category_id, $post_content);
    }
}
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Detail</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="js/kickstart.js"></script> <!-- KICKSTART -->
        <link rel="stylesheet" href="css/kickstart.css" media="all" /> <!-- KICKSTART -->
        <script src="js/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <form style="width: 50%; margin: auto;" action="" method="post" class="vertical">
            <fieldset>
                <a class="button blue small" href="index.php">Trang chủ</a>
                <legend>Bài viết</legend>
                
                <div class="notice success <?php if($r) {echo 'show';} else {echo 'hide'; }?>">Sửa bài viết thành công !!!</div>
                <label for="post_title">Tiêu đề:<span class="right"><?php if (!empty($error['post_title'])) {echo $error['post_title']; } ?></span></label>
                <input class="<?php if (!empty($error['post_title'])) {echo "error"; } ?>" type="text" name="post_title" id="post_title" value="<?php if (!empty($post_title)) { echo $post_title;} ?>"/>
                
                <label class="<?php if (!empty($error['post_desc'])) {echo "error"; } ?>" for="post_desc">Mô tả:<span class="right"><?php if (!empty($error['post_desc'])) {echo $error['post_desc']; } ?></span></label>
                <textarea type="text" name="post_desc" id="post_desc"><?php if (!empty($post_desc)) { echo $post_desc;} ?></textarea>
                
                <label for="post_thumb">Hình ảnh:<span class="right"><?php if (!empty($error['post_thumb'])) {echo $error['post_thumb']; } ?></span></label>
                <input class="<?php if (!empty($error['post_thumb'])) {echo "error"; } ?>" type="text" name="post_thumb" id="post_thumb" value="<?php if (!empty($post_thumb)) { echo $post_thumb;} ?>"/>
                
                <label for="category_id">Chuyên mục:</label>
                <select name="category_id" id="category_id">
                    <option <?php if (empty($category_id) || $category_id == 1){ echo "selected"; }?> value="1">Thời sự</option>
                    <option <?php if (!empty($category_id) && $category_id == 2){ echo "selected"; }?> value="2">Thể thao</option>
                    <option <?php if (!empty($category_id) && $category_id == 3){ echo "selected"; }?> value="3">Kinh tế</option>
                    <option <?php if (!empty($category_id) && $category_id == 4){ echo "selected"; }?> value="4">Chính trị</option>
                </select>
                
                <label class="<?php if (!empty($error['post_content'])) {echo "error"; } ?>" for="post_content">Nội dung:<span class="right"><?php if (!empty($error['post_content'])) {echo $error['post_content']; } ?></span></label>
                <textarea type="text" name="post_content" id="post_content"><?php if (!empty($post_content)) { echo $post_content;} ?></textarea>
                <script>
                // Replace the <textarea id="editor1"> with a CKEditor
                // instance, using default configuration.
                CKEDITOR.replace('post_content');
                </script>
                <button class="blue small" type="submit">Lưu</button>
            </fieldset>
        </form>
    </body>
</html>
