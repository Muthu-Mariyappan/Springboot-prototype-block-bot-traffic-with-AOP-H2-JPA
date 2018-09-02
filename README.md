## Springboot-prototype-block-bot-traffic-with-AOP-H2-JPA
--------------------------------------------------------

This project is a prototype to find and block bot access to REST API's. This project focuses on temporal aspect of bot access.
Bot accesses are tend to be so many requests in short time span. We exploit this behaviour to detect bot traffic in this project.


### Usage of Aspect oriented Programming
-----------------------------------------
> Target API to safeguard is a simple greetings module. I used AOP to prevent any modifications to actual business logic.

With the Help of AOP we can preserve the actual business logic, and can add extra utitilites without affecting the source.
So AOP acts as plugin, we can add or remove at will, without affecting actual application.

Please check out my similar project in AOP

https://github.com/Muthu-Mariyappan/IP-block-using-springboot-AOP

### H2-JPA

> H2 is a inmemory database, java based and can be embedded to our application. Used to store IP entry, hit time, no of hits.

Since it is non-persistent the content will be lost when the web application is shut down.

### Logic behind Botshield
---------------------------
The logic is quite simple. 

> When API is accessed from Party A, mark its is IP address and access time

> If the Party A accesses the API again under some n seconds, increase the count

> If the hit count reached m then, refuse access and throw 503 error.

> Reset the hit count after 8 hours, when party A again try to access the API

	Error is thrown using Custom error page with thymeLeaf

### Prototype

It is only a prototype, not ready for production use but following can be added to make it production ready

> Update the logic to respond to different temporal behaviors such as slow and continuous attack and etc..

> Implement OAuth to identify over active users

> Implement Captcha
