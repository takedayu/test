<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>  
<%
@SuppressWarnings("unchecked")
List<Tzaiko> list=(List<Tzaiko>)request.getAttribute("list");
@SuppressWarnings("unchecked")
List<Tzaiko> list2=(List<Tzaiko>)request.getAttribute("list2");
@SuppressWarnings("unchecked")
List<Tzaiko> list3=(List<Tzaiko>)session.getAttribute("list3");
%>

<!DOCTYPE html>

<html>
<head>

<style>

.indicator::before {
  content: "";
  height: 100px;
  width: 100px;
  position: absolute;
  border: 50px solid transparent;;
  border-bottom-color: #aaa;
  margin-top: -5px;
}

.indicator::after {
  content: "";
  height: 100px;
  width: 100px;
  position: absolute;
  border: 50px solid transparent;;
  border-top-color: #555;
  margin-top: 105px;
}


.listcss{
    overflow: auto;
     /*width: 100%;*/
    height: 400px;
}
.listcss table{
    margin: 0;
    border-spacing: 0;
    table-layout: fixed;
    width: 100%;

}

.listcss td{
    white-space: nowrap;
    border-right: 1px solid #999;
    border-bottom: 1px solid #999;
    background: #FFF;
    padding: 2px;
}

.listcss tr:nth-child(even) td{
	background: #ffffe0;
}

.listcss th{
	cursor: pointer;

	
    white-space: nowrap;
    border-right: 1px solid #999;
    border-bottom: 1px solid #999;
    background: #f2f2f2;
    position: sticky;
    top: 0;
    left: 0;
    
}



.listcss th::before, .listcss th::after {
  content: "";
  height: 0;
  width: 0;
  position: absolute;
  border: 5px solid transparent;
  right: 7px;
  top: 50%;
}

.listcss th::before {
    border-bottom-color: #aaa;
    margin-top: -10px;
}
.listcss th::after {
    border-top-color: #aaa;
    margin-top: 2px;
}





.errormessage {
	color: #ff0000;
}



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

body {
	width:90%;
    margin: 0 auto;
}

.label{
/* 背景の上から95％に色をつける */
background:linear-gradient(to bottom,#f2f2f2 0% 95%, rgba(0,0,0,0) 95% 100%);
}



</style>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>

'use strict';

$(function(){

  /**
   * クリックイベント
   */
  $('th').click(function(){
    // 情報取得
    let ele = $(this).attr('id');
    let sortFlg = $(this).data('sort');

    // リセット
    $('th').data('sort', "");

    // ソート順序
    if(sortFlg == "" || sortFlg == "desc"){
      sortFlg = "asc";
      $(this).data('sort', "asc");
    }else{
      sortFlg = "desc";
      $(this).data('sort', "desc");
    }

    // テーブルソート処理
    sortTable(ele, sortFlg);
  });

  /**
   * テーブルソートメソッド
   * 
   * @param ele 
   * @param sortFlg 
   */
  function sortTable(ele, sortFlg){
    let arr = $('table tbody tr').sort(function(a, b){
      // ソート対象が数値の場合
      if($.isNumeric($(a).find('td').eq(ele).text())){
        let aNum = Number($(a).find('td').eq(ele).text());
        let bNum = Number($(b).find('td').eq(ele).text());

        if(sortFlg == "asc"){
          return aNum - bNum;
        }else{
          return bNum - aNum;
        }
      }else{ // ソート対象が数値でない場合
        let sortNum = 1;

        // 比較時は小文字に統一
        if($(a).find('td').eq(ele).text().toLowerCase() > $(b).find('td').eq(ele).text().toLowerCase()){
          sortNum = 1;
        }else{
          sortNum = -1;
        }
        if(sortFlg == "desc"){
          sortNum *= (-1);
        }

        return sortNum;
      }
    });
    $('table tbody').html(arr);
  }

});



</script>



<!-- link rel="stylesheet" type="text/css" href="<!%= request.getContextPath() %>/style/style.css"> -->
<meta charset="UTF-8">
<title>在庫一覧</title>
</head>
<body>
<br>
<!-- 
<button class="buttoncss" type="button" onclick="window.open('/20220915kaihatsu/CreateMSTSEIHIN','null','menubar=0');">新規登録</button><br>
 -->
<form action="/20220915kaihatsu/SearchTZAIKO" method="post">

<label class="label" for="searchcode1">　　製品コード&nbsp;</label>
	<select name="searchcode" id="searchcode1">
	<option value="">製品コードを選択</option>
	<%for(Tzaiko box:list2){%>
		<option value=<%=box.getSeihincode()%>><%=box.getSeihincode()%></option>
	<%}%>
	</select>

<label class="label" for="searchname2">　　製品名&nbsp;</label>
<input type="search" id="searchname2" name="searchname">
<button class="buttoncss" type="submit">検索</button>

</form>

<!-- 
<!% if(list3!=null && list3.size() == 1){ %>
<script>
	alert('ORA-02292: 整合性制約に違反しています！')
</script>
<!%} %> -->
<div class="listcss">
<table border="1" id="table" >
<thead>
<tr>
<th width="30%" id="0" data-sort="">製品コード</th><th width="50%" id="1" data-sort="">製品名</th><th width="20%" id="2" data-sort="">数量</th>
</tr>
</thead>
<tbody>
<%
for(Tzaiko zaiko:list){
%>

<tr>
<!-- <td><!%=zaiko.getZaikono() %></td> -->
<td><%=zaiko.getSeihincode() %></td>
<td><%=zaiko.getSeihinname() %></td>
<td><%=zaiko.getZaikosuryo() %></td>
<td>
<!-- 
<button class="buttoncss" onClick="window.open('/20220915kaihatsu/UpdateMSTSEIHIN?SEIHIN_CODE=<!%=zaiko.getSeihincode() %>','null','menubar=0');" >更新</button>
<a class="buttoncss" href="/20220915kaihatsu/DeleteMSTSEIHIN?SEIHIN_CODE=<!%=zaiko.getSeihincode() %>" onclick="return confirm('製品コード<!%=zaiko.getSeihincode()%>を削除してよろしいですか？');">削除</a>
  -->
 <!-- 
<button onclick="deleteshori();">削除</button>
<script>
function deleteshori() {
	if (confirm('製品コード<!%=seihin.getSeihincode()%>を削除してよろしいですか？') == true){
		location.href="/20220915kaihatsu/DeleteMSTSEIHIN?SEIHIN_CODE=<!%=seihin.getSeihincode() %>";
		}
}
</script>
-->
</td>
</tr>
<%} %>
</tbody>



</table>
</div>
</body>
</html>