<?php $pdo = new PDO('mysql:host=mysql.hostinger.kr;dbname=u492528866_db', 'u492528866_user', 'wxg3276');
$statement = $pdo->query("SELECT * FROM  Articles");
while($e = $statement->fetch(PDO::FETCH_ASSOC))
      $output[]=$e;
print (json_encode($output));
var_dump(mysql_error());

?>