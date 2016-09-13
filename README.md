# Personal_Test(个人练习的合集)

1:下载网络图片Demo
-------
在主线程中开启一个Thread去通过httpclient下载网络上的图片
把图片变成bytearray 通过messag发送出去
然后通过主线程中的Handle去拿到这个message然后把它设置到imagview中

2:通过Message来进行IPC通讯
-------
通过在不同进程建立client跟server端，然后通过message来进行IPC跨进程的通讯
具体参考android探索艺术中的P65-71