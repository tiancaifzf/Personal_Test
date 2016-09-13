# Personal_Test
下载网络图片Demo
-------
在主线程中开启一个Thread去通过httpclient下载网络上的图片
把图片变成bytearray 通过messag发送出去
然后通过主线程中的Handle去拿到这个message然后把它设置到imagview中
