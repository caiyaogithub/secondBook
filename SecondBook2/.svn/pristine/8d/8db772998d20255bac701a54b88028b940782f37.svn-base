/**
 *  上传图片
 *  
 */
     
window.onload = function(){
	
	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'pickfiles', // you can pass in id...
		url : 'imageUpload',
		flash_swf_url : 'plupload/Moxie.swf',
		silverlight_xap_url : 'plupload/Moxie.xap',
		filters : {
			max_file_size : '10mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,gif,png,jpeg"},
			]
		},

		init: {
			PostInit: function() {
				document.getElementById('uploadfiles').onclick = function() {
					uploader.start();
					return false;
				};
			},

			FilesAdded: function(up, files) {
				for(var i = 0, len = files.length; i<len; i++){
					var file_name = files[i].name; //文件名
					//构造html来更新UI
					var html = "<div id = \"file-" + files[i].id + "\" style = \"display:inline;\" ><div id = \"progress\" style = \"display:inline;\"></div></div>" ;
					$(html).appendTo('#file-list');
					!function(i){
						previewImage(files[i],function(imgsrc){
							$('#file-'+files[i].id).append('<img src="'+ imgsrc +'" />');
						})
				    }(i);
				}
			},
			BeforeUpload: function(up, file) {
		    },
			UploadProgress: function(up, file) {
				/* document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>"; */
			},
	 		FileUploaded: function(up, file, info) {
	                // Called when file has finished uploading
					alert(info.response);
	        },
			Error: function(up, err) {
				/* document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message; */
			}
		}
	});

		//plupload中为我们提供了mOxie对象
		//有关mOxie的介绍和说明请看：https://github.com/moxiecode/moxie/wiki/API
		//如果你不想了解那么多的话，那就照抄本示例的代码来得到预览的图片吧
		function previewImage(file,callback){//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
			if(!file || !/image\//.test(file.type)) return; //确保文件是图片
			if(file.type=='image/gif'){//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
				var fr = new mOxie.FileReader();
				fr.onload = function(){
					callback(fr.result);
					fr.destroy();
					fr = null;
				}
				fr.readAsDataURL(file.getSource());
			}else{
				var preloader = new mOxie.Image();
				preloader.onload = function() {
					preloader.downsize( 60, 60 );//先压缩一下要预览的图片,宽300，高300
					var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
					callback && callback(imgsrc); //callback传入的参数为预览图片的url
					preloader.destroy();
					preloader = null;
				};
				preloader.load( file.getSource() );
			}	
		}
		
	uploader.init();
	
	
	
}
