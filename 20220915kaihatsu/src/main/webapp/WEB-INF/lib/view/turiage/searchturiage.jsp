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
/* ??????????????????95????????????????????? */
background:linear-gradient(to bottom,#f2f2f2 0% 95%, rgba(0,0,0,0) 95% 100%);
}


.buttoncss {
  display       : inline-block;
  font-size     : 12pt;        /* ??????????????? */
  text-align    : center;      /* ????????????   */
  cursor        : pointer;     /* ????????????   */
  padding       : 7px 8px;   /* ??????       */
  background    : #007749;     /* ?????????     */
  color         : #ffffff;     /* ?????????     */
  line-height   : 1em;         /* 1????????????  */
  opacity       : 1;         /* ?????????     */
  transition    : .3s;         /* ?????????????????? */
  border: none;/*????????????*/
  text-decoration: none;
}
.buttoncss:hover {
  background    : rgb(0,140,86);     /* ?????????     */
}

.buttoncss:active {
 /* border-bottom: solid 2px #fd9535;
  box-shadow: 0 0 2px rgba(0, 0, 0, 0.30);*/
  background    : #007749;     /* ?????????     */   */  
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
   * ????????????????????????
   */
  $('th').click(function(){
    // ????????????
    let ele = $(this).attr('id');
    let sortFlg = $(this).data('sort');

    // ????????????
    $('th').data('sort', "");

    // ???????????????
    if(sortFlg == "" || sortFlg == "desc"){
      sortFlg = "asc";
      $(this).data('sort', "asc");
    }else{
      sortFlg = "desc";
      $(this).data('sort', "desc");
    }

    // ???????????????????????????
    sortTable(ele, sortFlg);
  });

  /**
   * ?????????????????????????????????
   * 
   * @param ele 
   * @param sortFlg 
   */
  function sortTable(ele, sortFlg){
    let arr = $('table tbody tr').sort(function(a, b){
      // ?????????????????????????????????
      if($.isNumeric($(a).find('td').eq(ele).text())){
        let aNum = Number($(a).find('td').eq(ele).text());
        let bNum = Number($(b).find('td').eq(ele).text());

        if(sortFlg == "asc"){
          return aNum - bNum;
        }else{
          return bNum - aNum;
        }
      }else{ // ???????????????????????????????????????
        let sortNum = 1;

        // ??????????????????????????????
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
<title>????????????</title>
</head>
<body>
<br>

<button class="buttoncss" type="button" onclick="window.open('/20220915kaihatsu/CreateTURIAGE','null','menubar=0');">????????????</button><br>

<form action="/20220915kaihatsu/SearchTURIAGE" method="post">

<label class="label" for="searchno1">????????????No&nbsp;</label>
	<select name="searchno" id="searchno1">
	<option value="">??????No.???????????? </option>

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


	<label class="label" for="searchdate2">???????????????&nbsp;</label>
	<input type="date" id="searchdate2" name="searchdateST" value="<%= searchdateST %>">
	<p style="display:inline">???</p>
	<input type="date" id="searchdate2" name="searchdateEN" value="<%= searchdateEN %>">

<label class="label" for="searchkokyaku1">?????????????????????&nbsp;</label>
	<select name="searchkokyaku" id="searchkokyaku1">
	<option value="">????????????????????????</option>
	<%for(Turiage box:list2){%>
		<% if(box.getKokyakucode().equals(searchkokyaku)){	%>
		<option value=<%=box.getKokyakucode()%> selected><%=box.getKokyakucode()%></option>
	<%}else{%>
		<option value=<%=box.getKokyakucode()%>><%=box.getKokyakucode()%></option>
	<%}%>
	<%}%>

	</select>
	
<label class="label" for="searchseihin1">?????????????????????&nbsp;</label>
	<select name="searchseihin" id="searchseihin1">
	<option value="">????????????????????????</option>
	<%for(Turiage box:list2){%>
		<% if(box.getSeihincode().equals(searchseihin)){	%>
		<option value=<%=box.getSeihincode()%> selected><%=box.getSeihincode()%></option>
	<%}else{%>
		<option value=<%=box.getSeihincode()%>><%=box.getSeihincode()%></option>
	<%}%>
	<%}%>

	</select>

<button class="buttoncss" type="submit">??????</button>
<!-- button type="button" onclick=history.back()>??????</button> -->
</form>

<% if(list3!=null && list3.size() == 1){ %>
<script>
	alert('ORA-02292: ??????????????????????????????????????????')
</script>
<%} %>

<div class="listcss">
<table border="1" id="table">
<thead>
<tr>
<th width="10%" id="0" data-sort="">??????No.</th><th width="15%" id="1" data-sort="">?????????</th><th width="12%" id="2" data-sort="">???????????????</th><th width="12%" id="3" data-sort="">???????????????</th><th width="15%" id="4" data-sort="">??????</th><th width="31%" id="5" data-sort="">?????????</th><th width="5%"></th>
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
<a class="buttoncss" href="/20220915kaihatsu/DeleteTURIAGE?URIAGE_NO=<%=uriage.getUriageno() %>" onclick="return confirm('??????No.<%=uriage.getUriageno()%>???????????????????????????????????????');">??????</a>
</td>
</tr>
<%} %>
</tbody>
</table>
<% if(list.size() == 0){ %>
<p class="errormessage">????????????????????????????????????????????????????????????</p>
<%} %>
</div>
<!-- <p>(sessionID=<!%= session.getId() %>)</p> -->
</body>
</html>