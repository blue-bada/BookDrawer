<?php

$title = $_POST['title'];
$writer = $_POST['writer'];
$id = $_POST['id'];
$content = $_POST['content'];
$writeDate = $_POST['writeDate'];
$imgName = $_POST['imgName'];

if($title == "" || $writer == "" || $id == "" ||  $content == "" || $writeDate == "" || $ImgName == "")
{
echo "error";
} else {
$pdo = new PDO("mysql:host=mysql.hostinger.kr;dbname=u492528866_db", "u492528866_user", "wxg3276");
	
$st = $pdo->prepare("INSERT INTO Articles (Title, Writer, Id, Content, WriteDate, ImgName) VALUES ('$title', '$writer', '$id', '$content', '$writeDate', '$imgName');");
$st->execute();

unset($pdo); 
unset($st); 
	
echo "ok!";
}



	

	
?>