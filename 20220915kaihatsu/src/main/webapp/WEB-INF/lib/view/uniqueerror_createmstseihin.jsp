<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
Mstseihin tsuika=(Mstseihin)request.getAttribute("tsuika");
    //Mstseihin tsuika=(Mstseihin)request.getAttribute("tsuika");
%>
<!DOCTYPE html>
<html>
<head>


<style>

.buttoncss {
  display       : inline-block;
  font-size     : 12pt;        /* 文字サイズ */
  text-align    : center;      /* 文字位置   */
  cursor        : pointer;     /* カーソル   */
  padding       : 7px 8px;   /* 余白       */
  background    : #007749;     /* 背景色     */
  color         : #ffffff;     /* 文字色     */
  line-height   : 1em;         /* 1行の高さ  */
  opacity       : 1;         /* 透明度     */
  transition    : .3s;         /* なめらか変化 */
  border: none;/*線を消す*/
  text-decoration: none;
}
.buttoncss:hover {
  background    : rgb(0,140,86);     /* 背景色     */
}

.buttoncss:active {
 /* border-bottom: solid 2px #fd9535;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);*/
  background    : #007749;     /* 背景色     */   */  
}



.cancelbuttoncss {
  display       : inline-block;
  font-size     : 12pt;        /* 文字サイズ */
  text-align    : center;      /* 文字位置   */
  cursor        : pointer;     /* カーソル   */
  padding       : 7px 8px;   /* 余白       */
  background    : #808080;     /* 背景色     */
  color         : #ffffff;     /* 文字色     */
  line-height   : 1em;         /* 1行の高さ  */
  opacity       : 1;         /* 透明度     */
  transition    : .3s;         /* なめらか変化 */
  border: none;/*線を消す*/
  text-decoration: none;
}
.cancelbuttoncss:hover {
  background    : rgb(145,145,145);     /* 背景色     */
}

.cancelbuttoncss:active {
 /* border-bottom: solid 2px #fd9535;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);*/
  background    : #808080;     /* 背景色     */   */  
}

.label{
/* 背景の上から95％に色をつける */
background:linear-gradient(to bottom,#f2f2f2 0% 95%, rgba(0,0,0,0) 95% 100%);
}


/* NGなら赤くする */
form input:focus:invalid {
  border : tomato 2px solid;
}
/* 操作中、入力値が正しい場合は、不正エラーを消す */
form input:focus:valid + .error .invalid {
  display : none;
}


.valimessage {
	color: #ff0000;
}

.required:after {
	color: red;
    content: "*";
}

    p{
margin:0 0 0em; /*上 左右 下*/
}

</style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>

<p class="valimessage"><b>製品コードが既に登録されています！</b></p>

<p class="valimessage">*の項目は入力必須です。</p><br>

<form action="/20220915kaihatsu/CreateMSTSEIHIN" method="post">
<label class="label required" for="seihincode1">製品コード</label>
	<input id="seihincode1" type="text" name="SEIHIN_CODE" maxlength="5" value="<%=tsuika.getSeihincode() %>" required><br>
<label class="label required" for="seihinname1">　　製品名</label>
	<input id="seihinname1" type="text" name="SEIHIN_NAME" maxlength="20"value="<%=tsuika.getSeihinname() %>" required><br>
<label class="label" for="seihingenka1">　　　原価&nbsp;&nbsp;</label>
	<input id="seihingenka1" type="text" name="SEIHIN_GENKA" value="<%=tsuika.getSeihingenka() %>" pattern="[0-9]{0,10}" title="10桁以下の半角数字のみ入力可能です。"><br>
<label class="label" for="seihinteika1">　　　定価&nbsp;&nbsp;</label>
	<input id="seihinteika1" type="text" name="SEIHIN_TEIKA" value="<%=tsuika.getSeihinteika() %>" pattern="[0-9]{0,10}" title="10桁以下の半角数字のみ入力可能です。"><br><br>
<button class="buttoncss" type="submit">追加</button>
<button class="cancelbuttoncss" type="button" onclick="closeWin2()">閉じる</button>

<script>
function closeWin2() {
//	if (confirm('入力内容は破棄されます') == true){
		window.close();
//		}
}
window.onbeforeunload = function(e) {
	  return "ブラウザを閉じても良いでしょうか？"; // 文字列はメッセージに反映されません。必ずreturnすればブランクでもOKです。
}
</script>
</form>
</body>
</html>