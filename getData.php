<?php
$num = $_GET["num"];
$file_name = sprintf("data/output_q%d.txt", $num);

$txt_file    = file_get_contents($file_name);
$info        = explode("\n", $txt_file);

$charts = array();
$cols = array(array("id" => "key", "lable" => "Key", "type" => "string"), array("id" => "value", "lable" => "Ave Score", "type" => "number"));

foreach($info as $line => $data)
{
	$row_data = explode('	', $data);
	
	// title
    	if ($line == 0) {
		$title = $row_data[0] . ". " . $row_data[1];
		$row = array();
	} 
	elseif ($row_data[0] != "") {
		if (count($row_data) == 1) {
			$chartname = $row_data[0];
		} else {
			$row[$row_data[0]] = $row_data[1];
		}
	}
	else {
		if ($row && count($row) != 0) {
			$rows = array();
			foreach ($row as $row_k => $row_v) {
				$c = array("c" => array(array("v" => $row_k), array("v" => $row_v)));
				$rows[] = $c;
			}
			$chart = array("cols" => $cols, "rows" => $rows);
			$charts[$chartname] = $chart;
			$row = array();
		}
	}

}

echo json_encode(array("title" => $title, "charts" => $charts), JSON_UNESCAPED_UNICODE);

?>
