# ZuoBiao.me

This project is aim to analysis the data of http://zuobiao.me for the year 2014.

1. Deal with the raw data(CSV format)
2. Display every result by Google Chart API

------------------------------------------------------------------------------------

The first step is made by JAVA. Actually it would be better to use mySQL or other database.

  *  Resort.java  
Turn all results to numbers(for calculate average score).

  *  Question.java  
Encapsulate every question with number and content.

  *  SortByQ.java  
Divide all results by different question.

  *  AnalyseQuestion.java  
Calculate every average score by different kind.

I've remove some data as there are some impossible data (like age 14 and degree is Master) or quantity are too small (for people are older than 54)


Then for data presentation, I choose Google Charts API(https://developers.google.com/chart/?hl=en) as it really simple and free of charge.

  *  getData.php  
Deal with the data and encapsulate them to JSON.

  *  index.html  
Display data with column charts.

------------------------------------------------------------------------------------

As the UI are running in the virtual machine of my company, one must use VPN to view the sample.

