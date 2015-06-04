## 如何在Linux下安装并配置MongoDB
借此机会把关于项目过程中涉及的MongoDB的安装和简单使用方法在这里做些记录。
* **下载MongoDB**

MongoDB官网下载页面链接，[请点这里](https://www.mongodb.org/downloads). 

MongoDB针对Linux的不同发行版本编译发布出来的二进制文件在功能上有一些区别，具体的功能限制或者增强功能可以参考官网介绍。如果对自己的Linux环境具体的发行版本不熟悉可以下载Linux Legacy包。比如我自己用的是CentOS7，那我就下载RedHat7对应的tar包(mongodb-linux-x86_64-rhel70-3.0.3.tar)即可。除此之外还需要注意X86和X64区别。

* **归化目录以及安装2进制文件包**

二进制可执行文件，可以一份文件拷贝可以重复执行出多个进程实例。所以对于这种文件我们只需要把他放在一个公共的目录下，可以通过编写shell脚本并且把可执行文件加入Path来执行。

* 创建`~/lib` 或者 `~/binary-pkg`目录。 目录名称可以按照自己喜好自由命名，但是推荐可以使用一些公认的常用可执行目录名称，防止在定义多个环境变量时造成混乱。
* 将tar包拷贝至第一步创建的目录下，使用 `tar -zxvf mongodb-linux-x86_64-rhel70-3.0.3.tar`解压至当前目录。我有个习惯就是在多版本并且目录名很长的情况下创建软连接来让目录更清晰。创建软连接命令 `ln -s mongodb-linux-x86_64-rhel70-3.0.3 mongodb`.

* 这样的好处就是，如果哪天我需要升级mongodb的版本，只需要修改软连接即可。软连接创建完毕之后，目录列表如下：
````
lrwxrwxrwx. 1 cnode1 cnode1   33 Jun  5 08:52 mongodb -> mongodb-linux-x86_64-rhel70-3.0.3
drwxrwxr-x. 3 cnode1 cnode1   74 Jun  5 08:51 mongodb-linux-x86_64-rhel70-3.0.3
````
* 设置环境变量。 已CentOS为例，修改~/.bash_profile文件即可。对于不同的Linux发行版本profile文件可能会有差别，如果对自己的Linux版本不是很了解，请自行Google解决。 附上一个自己一直使用的模板：
````
# scripts for setting mongodb env
if [ -d "/home/cnode1/lib/mongodb" ] ; then
    MDB_HOME="/home/cnode1/lib/mongodb"
    export MDB_HOME
    # add mongodb bin to path if it exists
    if [ -d "$MDB_HOME/bin" ] ; then
        PATH="$MDB_HOME/bin:$PATH"
    fi
fi
````
使用这个模板，只需要修改二进制包的目录`/home/cnode1/lib/mongodb`。修改完保存文件，并`source .bash_profile`立即生效。

* 使用`mongod -version`检查环境变量是否设置成功，以及检查MongoDB是否可能正常使用。如果提示类似如下信息，就说明MongoDB第一步安装就OK了。
````
[cnode1@pmc lib]$ mongod -version
db version v3.0.3
git version: b40106b36eecd1b4407eb1ad1af6bc60593c6105
OpenSSL version: OpenSSL 1.0.1e-fips 11 Feb 2013
````

* **配置MongoDB**

continued...

