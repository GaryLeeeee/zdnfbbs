# 一个论坛
# coding by java smm
<p>整个项目的目录：</p>

![avatar](http://omspj9k7f.bkt.clouddn.com/git.PNG)

<p>分为两大类：<br>
    java/resources</p>
<p>java:<br>
controller:用来处理请求，返回响应视图/api数据。<br>
service:用来处理controller接受到的请求，并将处理结果返回controller。<br>
dao：连接数据库，采用写mapper的写法，mapper文件夹在resource/mapper。<br>
domain:存放实体类，用于数据绑定。<br>
tools:用来编写一些普通java类，完成特定工作。<br>
</p>
<p>resource:<br>
mapper:用于存放dao。<br>
static：用于存放css/js/img文件。<br>
templates:用来存放html文件。<br>
</p>
