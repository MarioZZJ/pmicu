function update() {
    var date = new Date()
    var utc8DiffMinutes = date.getTimezoneOffset() + 480
    date.setMinutes(date.getMinutes() + utc8DiffMinutes)

    var timeString = date.getHours() + ':' + ('0' + date.getMinutes()).slice(-2)
    var dateString = (date.getMonth() + 1) + '/' + date.getDate();
    var weekList = ['Sun.', 'Mon.', 'Tue.', 'Wed.', 'Thu.', 'Fri.', 'Sat.']
    var weekString = weekList[date.getDay()]

    document.getElementById("time").innerHTML = timeString
    document.getElementById("date").innerHTML = dateString
    document.getElementById("week").innerHTML = weekString

    var day = date.getDay();
    var hour = date.getHours();
    var minute = date.getMinutes();
    var t = date.getHours() + ('0' + date.getMinutes()).slice(-2);
    t = parseInt(t);
    var sch = "";
    var brkflag = false;
    
    var timeblock = [
      800,845,
      850,935,
      950,1035,
      1040,1125,
      1130,1215,
      1405,1450,
      1455,1540,
      1545,1630,
      1640,1725,
      1730,1815,
      1830,1915,
      1920,2005,
      2010,2055
      ];
    
    var schedule = [
      ["","","","","","","","","","","","",""],
      ["","","","","","","","","","","社会网络分析","社会网络分析","社会网络分析"],
      ["信息系统分析与设计（理论）","信息系统分析与设计（理论）","","","","","","","","","","",""],
      ["概率论与数理统计C","概率论与数理统计C","概率论与数理统计C","","","专业英语","专业英语","专业英语","大数据安全与保密","大数据安全与保密","","",""],
      ["","","","社会计算","社会计算","","","","","","","",""],
      ["大学英语4","大学英语4","摄影技术基础","摄影技术基础","摄影技术基础","","","","","","","",""],
      ["","","","","","","","","","","","",""],
    ]
    if(t>=800&&t<2100){
      brkflag==false;
      for(i=0;i<timeblock.length-1;i++){
        if(t>=timeblock[i]&&t<timeblock[i+1]){
          if(i%2==0){brkflag=false;}
          else {brkflag=true;}
          sch=schedule[day][Math.floor((i+1)/2)];
          break;
        }
      }
    }
    else{sch=="";}
    if(brkflag){
      sch="[课间]"+sch;
      document.getElementById("n").innerHTML = "〖即将上课〗"
    }
    else{document.getElementById("n").innerHTML = "〖当前课程〗"}

    if(sch==""||sch=="[课间]"){sch="今日事，今日毕";}

    document.getElementById("m").innerHTML = sch;

  }
  update()
  setInterval("update()", 60 * 1000)