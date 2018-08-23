var rowcounter = 0;
var checkboxs = 0;

function CreateCheckBox(rownum)
{

    checkboxs++;
    var row = document.getElementById("row" + rownum);


    td = document.createElement('td');
    td.id = "chktd" + rownum;
    td.rowSpan = 1;

    var chkbox = document.createElement('input');
    chkbox.type = "checkbox";
    chkbox.name = "chk" + rownum;

    td.appendChild(chkbox);

    row.appendChild(td);
}

function RemoveCheckBox(rownum) {

    var row = document.getElementById("row" + rownum);
    var col = document.getElementById("chktd" + rownum);

    row.removeChild(col);

    checkboxs--;

}
function RemoveRow() {

    var tbl = document.getElementById("table1");
    var row = document.getElementById("row" + rowcounter);

    rowchildscount = row.childElementCount-1;

    tbl.removeChild(row);

    checkboxs-=rowchildscount;
    rowcounter--;

}
function CreateRow()
{
    rowcounter++;

    var tbl = document.getElementById("table1");
    //Second create a row
    var row = document.createElement('tr');
    row.id = "row" + rowcounter;

    td = document.createElement('td');
    td.rowSpan = 1;


    var addbut = document.createElement('input');
    addbut.type = "button";
    addbut.value = "+";
    addbut.style="height:30px;width:30px;padding:0px";
    addbut.setAttribute("onclick","CreateCheckBox("+rowcounter+")");

    var removebut = document.createElement('input');
    removebut.type = "button";
    removebut.value = "-";
    removebut.style="height:30px;width:30px;padding:0px";
    removebut.setAttribute("onclick","RemoveCheckBox("+rowcounter+")");

    td.appendChild(addbut);
    td.appendChild(removebut);

    row.appendChild(td);

    //Finally added to the form to print
    tbl.appendChild(row);

}

function makeJson() {
    var obj = new Object();
    obj.rowcount = rowcounter;
    obj.checkboxs  = checkboxs;


    var listOfList = [];
    var listOfChks = [];
    for (var i = 1; i <= rowcounter; i++) {
        var chks = document.getElementsByName("chk" + i);
        for(var j = 0; j < chks.length; j++)
        {
            listOfChks.push(chks[j].checked);
        }

        listOfList.push(listOfChks);
        listOfChks = [];

    }
    obj.matrOfchecks = listOfList;



    document.getElementById("jsonString").value = JSON.stringify(obj);

}
