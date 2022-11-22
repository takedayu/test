<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
Mstseihin koushin=(Mstseihin)request.getAttribute("koushin");
//String searchname = (String)session.getAttribute("searchname");
//String searchcode = (String)session.getAttribute("searchcode");

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
<title>Insert title here</title>
</head>
<body>
<form name="updatem" action="/20220915kaihatsu/UpdateMSTSEIHIN" method="Post" onsubmit="return kakunin();">
<p class="valimessage">*の項目は入力必須です。</p><br>
<label class="label">製品コード&nbsp;&nbsp;</label>
<p style="display:inline"><%= koushin.getSeihincode() %></p><br>
<!-- 製品コード：<input type="text" name="SEIHIN_CODE" value="<!%=koushin.getSeihincode() %>"><br> -->
			<input type="hidden" name="SEIHIN_CODE" value="<%=koushin.getSeihincode() %>">
			
<label class="label required" for="seihinname1">　　製品名</label>
	<input id="seihinname1" type="text" name="SEIHIN_NAME" maxlength="20" required value="<%=koushin.getSeihinname() %>"><br>
	
<label class="label" for="seihingenka1">　　　原価&nbsp;&nbsp;</label>
	<input id="seihingenka1" type="text" name="SEIHIN_GENKA" value="<%=koushin.getSeihingenka()%>"
	pattern="[0-9]{0,10}" title="10桁以下の半角数字のみ入力可能です。"><br>
	
<label class="label" for="seihinteika1">　　　定価&nbsp;&nbsp;</label>
	<input id="seihinteika1" type="text" name="SEIHIN_TEIKA" value="<%=koushin.getSeihinteika() %>"
	pattern="[0-9]{0,10}" title="10桁以下の半角数字のみ入力可能です。"><br>
<br>
<button class="buttoncss" type="submit">更新</button>
<!-- <button name="btn" onclick="confirmsubmit()">更新</button> -->

<button class="cancelbuttoncss" type="button" onclick="closeWin2();">閉じる</button>

<script>
function kakunin() {
	if(confirm('更新してよろしいですか？') == true){
		window.onbeforeunload=null;
		return true;
	}else{
		return false;
	}
}

function closeWin2() {
//	if (confirm('更新を中断しますか？') == true){
		window.close();
//		}
}
window.onbeforeunload = function(e) {
	  return "ブラウザを閉じても良いでしょうか？"; // 文字列はメッセージに反映されません。必ずreturnすればブランクでもOKです。
}
</script>

<script>
/*
function confsub() {
	if (confirm('更新してよろしいですか？') == true){
		return true
		}
	return false;
}

function confirmsubmit() {
	if (confirm('更新してよろしいですか？') == true){
		document.updatem.submit();
		}
	return false;
}
*/

/*
document.updatem.btn.addEventListener('click', function() {
    document.updatem.submit();
});
*/
			<!--	  //formオブジェクトを取得する
				  var fm = document.getElementById("fm1");
				  //Submit値を操作したい場合
				  document.getElementById("1").value = "値を自動入力";
				  document.getElementById("2").value = "値を自動入力";
				  document.getElementById("3").value = "値を自動入力";
				  document.getElementById("4").value = "値を自動入力";
				  //Submit実行
				  fm.submit();
				  //画面を閉じる
				  window.close();
				}-->
ttt



				
</script>

</form>
</body>
</html>