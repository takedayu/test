<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*" import="controller.*,java.util.*"
    %>
    
<%
@SuppressWarnings("unchecked")
List<Turiage> list=(List<Turiage>)session.getAttribute("list");
@SuppressWarnings("unchecked")
List<Turiage> list2=(List<Turiage>)session.getAttribute("list2");
@SuppressWarnings("unchecked")
List<Turiage> list3=(List<Turiage>)session.getAttribute("list3");


//Integer searchno2 = (Integer)session.getAttribute("searchno");
//int searchno = searchno2;
String searchno2 = (String)session.getAttribute("searchno");

Date searchdateST = (Date)session.getAttribute("searchdateST");
Date searchdateEN = (Date)session.getAttribute("searchdateEN");
String searchkokyaku = (String)session.getAttribute("searchkokyaku");
String searchseihin = (String)session.getAttribute("searchseihin");

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

.label{
/* 背景の上から95％に色をつける */
background:linear-gradient(to bottom,#f2f2f2 0% 95%, rgba(0,0,0,0) 95% 100%);
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

});</script>





<meta charset="UTF-8">
<title>製品一覧</title>
</head>
<body>
<br>

<button class="buttoncss" type="button" onclick="window.open('/20220915kaihatsu/CreateTURIAGE','null','menubar=0');">新規登録</button><br>

<form action="/20220915kaihatsu/SearchTURIAGE" method="post">

<label class="label" for="searchno1">　　売上No&nbsp;</label>
	<select name="searchno" id="searchno1">
	<option value="">売上No.を選択　 </option>

	<%if (searchno2 != ""){
		int searchno=Integer.parseInt(searchno2);
		for(Turiage box:list2){	
			if(box.getUriageno() == searchno){%>
			<option value=<%=box.getUriageno()%> selected><%=box.getUriageno()%></option>
			<%}else{%>
			<option value=<%=box.getUriageno()%>><%=box.getUriageno()%></option>
			<%}%>
		<%}%>
	<%}else{%>
			<%for(Turiage box:list2){%>
			<option value=<%=box.getUriageno()%>><%=box.getUriageno()%></option>
			<%}%>
	<%}%>

	</select>


	<label class="label" for="searchdate2">　　取引日&nbsp;</label>
	<input type="date" id="searchdate2" name="searchdateST" value="<%= searchdateST %>">
	<p style="display:inline">～</p>
	<input type="date" id="searchdate2" name="searchdateEN" value="<%= searchdateEN %>">

<label class="label" for="searchkokyaku1">　　顧客コード&nbsp;</label>
	<select name="searchkokyaku" id="searchkokyaku1">
	<option value="">顧客コードを選択</option>
	<%for(Turiage box:list2){%>
		<% if(box.getKokyakucode().equals(searchkokyaku)){	%>
		<option value=<%=box.getKokyakucode()%> selected><%=box.getKokyakucode()%></option>
	<%}else{%>
		<option value=<%=box.getKokyakucode()%>><%=box.getKokyakucode()%></option>
	<%}%>
	<%}%>

	</select>
	
<label class="label" for="searchseihin1">　　製品コード&nbsp;</label>
	<select name="searchseihin" id="searchseihin1">
	<option value="">製品コードを選択</option>
	<%for(Turiage box:list2){%>
		<% if(box.getSeihincode().equals(searchseihin)){	%>
		<option value=<%=box.getSeihincode()%> selected><%=box.getSeihincode()%></option>
	<%}else{%>
		<option value=<%=box.getSeihincode()%>><%=box.getSeihincode()%></option>
	<%}%>
	<%}%>

	</select>

<button class="buttoncss" type="submit">検索</button>
<!-- button type="button" onclick=history.back()>戻る</button> -->
</form>

<% if(list3!=null && list3.size() == 1){ %>
<script>
	alert('ORA-02292: 整合性制約に違反しています！')
</script>
<%} %>

<div class="listcss">
<table border="1" id="table">
<thead>
<tr>
<th width="10%" id="0" data-sort="">売上No.</th><th width="15%" id="1" data-sort="">取引日</th><th width="12%" id="2" data-sort="">顧客コード</th><th width="12%" id="3" data-sort="">製品コード</th><th width="15%" id="4" data-sort="">数量</th><th width="31%" id="5" data-sort="">売上高</th><th width="5%"></th>
</tr>
</thead>
<tbody>
<%
for(Turiage uriage:list){
%>

<tr>
<td><%=uriage.getUriageno() %></td>
<td><%=uriage.getUriagedate() %></td>
<td><%=uriage.getKokyakucode() %></td>
<td><%=uriage.getSeihincode() %></td>
<td><%=uriage.getUriagesuryo() %></td>
<td><%=uriage.getUriage() %></td>
<td>
<a class="buttoncss" href="/20220915kaihatsu/DeleteTURIAGE?URIAGE_NO=<%=uriage.getUriageno() %>" onclick="return confirm('売上No.<%=uriage.getUriageno()%>を削除してよろしいですか？');">削除</a>
</td>
</tr>
<%} %>
</tbody>
</table>
<% if(list.size() == 0){ %>
<p class="errormessage">検索条件に一致するデータが存在しません！</p>
<%} %>
</div>
<!-- <p>(sessionID=<!%= session.getId() %>)</p> -->
</body>
</html>