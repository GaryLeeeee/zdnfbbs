$(document).ready(function(){
 window.emojiPicker = new EmojiPicker({
        emojiable_selector: '[data-emojiable=true]',
        assetsPath: 'lib/img/',
        popupButtonClasses: 'fa fa-smile-o'
          
      });
  window.emojiPicker.discover();
      $("div").each(function(){
        if($(this).attr('data-type')=='input'){
          $(this).attr('id','editor');
        }
      })
  var picUploader = Qiniu.uploader({

    runtimes: 'html5,flash,html4', // 上传模式，依次退化
    browse_button: 'filesUpload', // 上传选择的点选按钮
    get_new_uptoken: 'true',
    domain: 'zdnfbbs',
    max_file_size: '10mb',
    unique_names: false,
    save_key: false,
    uptoken_func: function() { // 在需要获取uptoken时，该方法会被调用
      var uptoken;
      var timestamp = Date.parse(new Date());
      var postId = checkPostCookie();
      var key = "";
      $.ajax({
        url: "api/replay/max?id=" + postId,
        async: false,
        success: function(data) {
          var ReplyNum = parseInt(data) + 1;
          key = "id_" + postId + "reply_" + ReplyNum + timestamp;
          $("body").append("<input id='key' type='hidden' value='" + key + "'/>");
          $.ajax({
            url: "/api/user/token?filename=" + key,
            async: false,
            success: function(data) {
              uptoken = data;
            },
          });
        },
      });

      return uptoken;
    },
    chunk_size: '1mb', // 分块上传时，每块的体积
    auto_start: true,
    init: {
      'FilesAdded': function(up, files) {
        plupload.each(files, function(file) { // 文件添加进队列后，处理相关的事情
          $(".sand").html("等待");
        });
      },
      'BeforeUpload': function(up, file) {
        // 每个文件上传前，处理相关的事情

        console.log("上传准备" + up);

      },
      'UploadProgress': function(up, file) {
        // 每个文件上传时，处理相关的事情
        console.log("上传中" + up);
      },
      'FileUploaded': function(up, file, info) {
        // 每个文件上传成功后，处理相关的事情
        // 其中info是文件上传成功后，服务端返回的json，形式如：
        // {
        //    "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
        //    "key": "gogopher.jpg"
        //  }
        // 查看简单反馈
        // var domain = up.getOption('domain');
        // var res = JSON.parse(info);
        var res = JSON.parse(info.response);
        var sourceLink = "http://pic.zdnfbbs.cn/" + res.key; //获取上传成功后的文件的Url
        var imgLink = Qiniu.imageMogr2({
          auto_orient: true,
          strip: true, // 布尔值，是否去除图片中的元信息
          thumbnail: '!50%p', // 缩放操作参数
          quality: 40, // 图片质量，取值范围1-100
          format: 'jpg', // 新图的输出格式，取值范围：jpg，gif，png，webp等
          size_limit: '300kb!',
        }, res.key);
        var editor = document.getElementById('editor');
        var ImageDiv = "<img src='" + sourceLink + "'class='img' style='width:100%;overflow-x:hidden;height:auto;'>";
        editor.focus();
        document.execCommand('insertHTML', false, ImageDiv);
        $("#filesUpload").attr('id','Null');
      },
      'Error': function(up, err, errTip) {
        //上传出错时，处理相关的事情
        alert(errTip);

      },
      'UploadComplete': function() {
        //队列文件处理完毕后，处理相关的事情
        $(".sand").html("发送");
      },
      'Key': function(up, file) {
        // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
        // 该配置必须要在unique_names: false，save_key: false时才生效
        var key = $('#key').val();



        // do something with key here
        return key
      }
    },


  });
replyNew();
});