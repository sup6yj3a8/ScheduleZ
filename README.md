# ScheduleZ資訊
## 基本資訊
* 名稱：排程力 Schedule Z
* 作者：呂宗霖（演算法、資料結構）、李建廷（輸出介面、功能整合）、賴彥潔（UI設計、輸入介面、串Google map API）
* 程式語言：Java
* Android版本：8.0 (Oreo)，可兼容約82.7%的設備

![想要解決的問題](https://blogger.googleusercontent.com/img/a/AVvXsEiXnetYbLVBnVaNdbJbyoz1OLBpm2CQBxqbxhF4MOz_4VvN27ArQyJER1shCvRhUAMjS-L5uSVV3nbaQVhcoFJwG6a48_cWQ1yEV1Z4kGegOHTgk4rWoJChOBSpSQQSl40dHNsk7cJgkmrSrBNXUNNUWOF6SrgalYrmfEpxk0DhI44uToyXX-3Cjtj4=w640-h360)
## 想要解決的問題
1. 客群：業務（客戶拜訪）、旅行者（景點安排）、出差者（工作規劃）
2. 問題：有時間窗的旅行推銷員問題 (Traveling Salesman Problem with Time Windows, TSPTW)
3. 情境：有位旅行者想要安排五天四夜的自由行，手邊有10個想要去的景點，但尚未安排明確時間，同時過夜的飯店地點時間已經決定好了。這些景點，旅行者希望景點(1)-(5)要排在第1-3天、景點(6)-(7)要排在第4-5天、景點(8)~(9)要排在第2-4天、景點(10)要排在第5天。因此旅行者希望能規劃出最短路徑的旅行行程，要符合旅行者要求且不能與舊行程牴觸。

## ScheduleZ價值
1. 在實用性上，可應用在客戶拜訪、景點安排、工作規劃，具有娛樂與商業價值
2. 在新穎性上，google日曆與地圖並無提供這類的整合性的最佳規劃
3. 在技術性上，TSPTW問題是屬於NP-Hard，演算法需特別設計，才能有好的品質。

# Blogger
![軟體使用說明](https://blogger.googleusercontent.com/img/a/AVvXsEgmBjtlWDtUoMCpFibp9L-xKF-E4bNsmUOKKJ794BSOPlPxs1f8yZq0uBiK9eVXtOhkW7XSeUETSxjDC3M4s7eFDjiQh6QA9zNjSvuTgqwaAQqAqTWAq1DXs6r8PDWNeeJXwlkIeEMNviWPEMmqcMCY3EgwooqqNK5GUdTxkaE0BmBh31AfOGwOCOQ0=s1920)
1. [排程力 Schedule Z 1：軟體使用說明](https://tsungsquare.blogspot.com/2022/01/ScheduleZ-1.html)
2. [排程力 Schedule Z 2：演算法介紹｜VIG_VNS for TSPTW](https://tsungsquare.blogspot.com/2022/01/ScheduleZ-2.html)
3. [排程力 Schedule Z 3：結果、結論與心得](https://tsungsquare.blogspot.com/2022/01/ScheduleZ-3.html)
4. [Schedule Z載點](https://github.com/sup6yj3a8/ScheduleZ/blob/master/app/apk/ScheduleZ.apk)
