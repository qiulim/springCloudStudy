#springCloudStudy

http://localhost:8000/currency-exchange/from/USD/to/CN/
http://localhost:8100/currency-convert-feign/from/USD/to/CN/quantity/1000

http://localhost:8761/

http://localhost:8765/currency-conversion-service/currency-convert-feign/from/USD/to/CN/quantity/1000
http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/CN

Ubuntu下安装rabbitMQ
参照网站：
https://jingyan.baidu.com/article/425e69e6f63990be15fc1621.html
https://www.cnblogs.com/hongdada/p/7203589.html

sudo apt-get install erlang-nox
sudo apt-get update
sudo apt-get install rabbitmq-server

启动、停止、重启、状态rabbitMq命令：

启动：sudo rabbitmq-server start
关闭： sudo rabbitmq-server stop
重启： sudo rabbitmq-server restart
查看状态：sudo rabbitmqctl status


安装好了以后可以使用 ps -ef|grep rabbit查看RabbitMQ的情况

cd /usr/lib/rabbitmq/bin/
sudo ./rabbitmqctl status

**rabbitMQ安装完后，远程访问时候，登录不进的解决方案**

