<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%
    //Mstseihin tsuika=(Mstseihin)request.getAttribute("tsuika");
@SuppressWarnings("unchecked")
List<Turiage> list2=(List<Turiage>)request.getAttribute("list2");
@SuppressWarnings("unchecked")
List<Tzaiko> zaikolist=(List<Tzaiko>)request.getAttribute("zaikolist");
@SuppressWarnings("unchecked")
List<Mstkokyaku> kokyakulist=(List<Mstkokyaku>)request.getAttribute("kokyakulist");
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


/*
    .seihinlist {

    }
    .fukidashi {
      display: none;
      position: absolute;
      top:75px;
      left: 262px;
      padding: 2px;
      border-radius: 5px;
      background: #ffffff;
      font-weight: bold;
      font-size     : 11pt;
      border: 1px solid black;
      font-weight: normal;
    }
    .fukidashi:before {
      position: absolute;
      width: 0;
      height: 0;
      left: -31px;
      bottom: 0px;
      top:14px;
      margin-left: 10px;
      border: solid transparent;
      border-top-color: #000000;
      border-width: 10px;
      pointer-events: none;
      content: " ";
      transform:rotate(90deg);
    }
    .fukidashi:after {
      position: absolute;
      width: 0;
      height: 0;
      left: -30px;
      bottom: 0px;
      top:14px;
      margin-left: 10px;
      border: solid transparent;
      border-top-color: #ffffff;
      border-width: 10px;
      pointer-events: none;
      content: " ";
      transform:rotate(90deg);
    }
    .seihinlist:focus + .fukidashi {
      display: block;

    }
*/
    p{
margin:0 0 0em; /*上 左右 下*/
}


</style>


<script>
/*
	function closePopUp() {
	  fukidashi.style.display = "none";
	}
	function openPopUp() {
	  closePopUp();
	  fukidashi.style.display = "block";
	}
*/

</script>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<p class="valimessage">*の項目は入力必須です。</p>
<p>※製品コードに項目が見当たらない場合は、在庫登録を行ってください。</p><br>
<form action="/20220915kaihatsu/CreateTURIAGE" method="post">
<label class="label required" for="uriagedate1">　　　取引日</label>
	<input id="uriagedate1" type="date" name="URIAGE_DATE" required><br>
	 
	 <label class="label required" for="kokyakucode1">　顧客コード</label>
	<select name="KOKYAKU_CODE" id="kokyakucode1" required>
	<option value="">顧客コードを選択</option>
	<%for(Mstkokyaku box:kokyakulist){%>
		<option value=<%=box.getKokyaku_code()%>><%=box.getKokyaku_code()%></option>
	<%}%>
	</select><br>
	
	
<label class="label required" for="seihincode1">　製品コード</label>
	<select class="seihinlist" name="SEIHIN_CODE" id="seihincode1" required>
	<option value="">製品コードを選択</option>
	<%for(Tzaiko box:zaikolist){%>
		<option value=<%=box.getSeihincode()%>><%=box.getSeihincode()%></option>
	<%}%>
	</select>
	<!-- <p class="fukidashi">項目が見当たらない場合は、<br>
							製品コードを在庫表に登録してください。</p> -->
	<br>
	
<label class="label" for="uriagesuryo1">　　　　数量&nbsp;&nbsp;</label>
	<input id="uriagesuryo1" type="text" name="URIAGE_SURYO" pattern="[0-9]{0,10}" title="10桁以下の半角数字のみ入力可能です。"><br>
<label class="label" for="uriage1">　　　売上高&nbsp;&nbsp;</label>
	<input id="uriage1" type="text" name="URIAGE" pattern="[0-9]{0,10}" title="10桁以下の半角数字のみ入力可能です。"><br><br>
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