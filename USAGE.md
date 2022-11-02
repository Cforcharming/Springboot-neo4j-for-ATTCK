# ATT&CK知识图谱增删改查后端使用说明

本后端支持基本的增删改查操作，以下为详情

## 1. 返回码与含义

| 返回码 | 含义             |
|:---:|:---------------|
|  0  | OK             |
|  1  | REQUEST_ERROR  |
|  2  | INTERNAL_ERROR |
|  3  | NO_RECORD      |
|  4  | ALREADY_EXIST  |

详见 `uestc.zhanghanwen.ATTCK.Wrappers.ResponseWrapper`

## 2. 查询操作

### URL

> http://localhost:8080/query
> 
> method: `GET`, `POST`

### 支持查询方式

1. 提交HTTP参数查询名称或Mitre ID
   
   #### 案例1
   
   > MockHttpServletRequest:  
   > HTTP Method = POST  
   > Request URI = /query  
   > Parameters = {name=[Initial Access]}  
   > Headers = []  
   > Body = null  
   > Session Attrs = {}
   > 
   > Handler:  
   > Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController  
   > Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryObject(String, String)  
   > Async:  
   > Async started = false  
   > Async result = null
   > 
   > Resolved Exception:  
   > Type = null
   > 
   > ModelAndView:  
   > View name = null  
   > View = null  
   > Model = null
   > 
   > FlashMap:  
   > Attributes = null
   > 
   > MockHttpServletResponse:  
   > Status = 200  
   > Error message = null  
   > Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"579"]  
   > Content type = text/plain;charset=UTF-8  
   > Body = {"status":0,"result":[{"description":"The adversary is trying to get into your network.\n\nInitial Access consists of techniques that use various entry vectors to gain their initial foothold within a network. Techniques used to gain a foothold include targeted spearphishing and exploiting weaknesses on public-facing web servers. Footholds gained through initial access may allow for continued access, like valid accounts and use of external remote services, or may be limited-use due to changing passwords.","mitre_id":"TA0001","name":"Initial Access"}],"msg":"OK","detail":""}  
   > Forwarded URL = null  
   > Redirected URL = null  
   > Cookies = []
   
   #### 案例2
   
   > MockHttpServletRequest:  
   > HTTP Method = GET  
   > Request URI = /query  
   > Parameters = {id=[MT0001]}  
   > Headers = []  
   > Body = null  
   > Session Attrs = {}  
   > 
   > Handler:  
   > Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController  
   > Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryObject(String, String)  
   > 
   > Async:  
   > Async started = false  
   > Async result = null  
   > 
   > Resolved Exception:  
   > Type = null  
   > 
   > ModelAndView:  
   > View name = null  
   > View = null  
   > Model = null  
   > 
   > FlashMap:  
   > Attributes = null  
   > 
   > MockHttpServletResponse:  
   > Status = 200  
   > Error message = null  
   > Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"81"]  
   > Content type = text/plain;charset=UTF-8  
   > Body = {"status":0,"result":[{"mitre_id":"MT0001","name":"pre"}],"msg":"OK","detail":""}  
   > Forwarded URL = null  
   > Redirected URL = null  
   > Cookies = []  

2. REST URL 查询 ID
   
   > HTTP Method = GET  
   > Request URI = /query/TA0001  
   > Parameters = {}  
   > Headers = []  
   > Body = null  
   > Session Attrs = {}
   > 
   > Handler:  
   > Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController  
   > Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryObjectByMitreId(String)
   > 
   > Async:  
   > Async started = false  
   > Async result = null
   > 
   > Resolved Exception:  
   > Type = null
   > 
   > ModelAndView:  
   > View name = null  
   > View = null  
   > Model = null
   > 
   > FlashMap:  
   > Attributes = null
   > 
   > MockHttpServletResponse:  
   > Status = 200  
   > Error message = null  
   > Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"579"]  
   > Content type = text/plain;charset=UTF-8  
   > Body = {"status":0,"result":[{"description":"The adversary is trying to get into your network.\n\nInitial Access consists of techniques that use various entry vectors to gain their initial foothold within a network. Techniques used to gain a foothold include targeted spearphishing and exploiting weaknesses on public-facing web servers. Footholds gained through initial access may allow for continued access, like valid accounts and use of external remote services, or may be limited-use due to changing passwords.","mitre_id":"TA0001","name":"Initial Access"}],"msg":"OK","detail":""}  
   > Forwarded URL = null  
   > Redirected URL = null  
   > Cookies = []

3. 查询某一类型
   
   #### 案例1，分页
   
   > MockHttpServletRequest:  
   > HTTP Method = GET  
   > Request URI = /query/type/technique  
   > Parameters = {page=[5]}  
   > Headers = []  
   > Body = null  
   > Session Attrs = {}
   > 
   > Handler:  
   > Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController  
   > Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryType(String, boolean, int, int)
   > 
   > Async:  
   > Async started = false  
   > Async result = null
   > 
   > Resolved Exception:  
   > Type = null
   > 
   > ModelAndView:  
   > View name = null  
   > View = null  
   > Model = null
   > 
   > FlashMap:  
   > Attributes = null
   > 
   > MockHttpServletResponse:  
   > Status = 200  
   > Error message = null  
   > Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"23987"]  
   > Content type = text/plain;charset=UTF-8  
   > Body = {"status":0,"result":[{...},...,{...}],"msg":"OK","detail":""}  
   > Forwarded URL = null  
   > Redirected URL = null  
   > Cookies = []
   
   #### 案例2， 不分页
   
   > MockHttpServletRequest:
   > HTTP Method = GET
   > Request URI = /query/type/matrix
   > Parameters = {get_all=[true]}
   > Headers = []
   > Body = null
   > Session Attrs = {}
   > 
   > Handler:
   > Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController
   > Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryType(String, boolean, int, int)
   > 
   > Async:
   > Async started = false
   > Async result = null
   > 
   > Resolved Exception:
   > Type = null
   > 
   > ModelAndView:
   > View name = null
   > View = null
   > Model = null
   > 
   > FlashMap:
   > Attributes = null
   > 
   > MockHttpServletResponse:
   > Status = 200
   > Error message = null
   > Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"123"]
   > Content type = text/plain;charset=UTF-8
   > Body = {"status":0,"result":[{"mitre_id":"MT0001","name":"pre"},{"mitre_id":"MT0002","name":"enterprise"}],"msg":"OK","detail":""}
   > Forwarded URL = null
   > Redirected URL = null
   > Cookies = []

4. 查询相关
   
   #### 案例1，查询某一个节点为起始的所有相关
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /query/MT0001/related  
   >  Parameters = {}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryRelatedByStartNodeMitreId(String)
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null
   > 
   > Resolved Exception:  
   >  Type = null
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null
   > 
   > FlashMap:  
   >  Attributes = null
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"6091"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[{"original":{"mitre_id":"MT0001","name":"pre"}},{"related":[{...},...,{...}]}],"msg":"OK","detail":""}  
   >  Forwarded URL = null  
   >  Redirected URL = null  
   >  Cookies = []
   
   #### 案例2，查询两个节点之间的关系
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /query/related  
   >  Parameters = {start_id=[MT0002], end_id=[TA0001]}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.RetrieveController#queryRelationshipByStartAndEndNodeMitreId(String, String)
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null
   > 
   > Resolved Exception:  
   >  Type = null
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null
   > 
   > FlashMap:  
   >  Attributes = null
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"66"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[{"name":"contains"}],"msg":"OK","detail":""}  
   >  Forwarded URL = null  
   >  Redirected URL = null  
   >  Cookies = []

## 3. 增加操作

### URL

> http://localhost:8080/create
> 
> method: `GET`, `POST`

### 支持增加方式

1. 增加节点
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /create/node
   >  Parameters = {value=[{"name":"test1","mitre_id":"A","type":"matrix"}]}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}  
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.CreateController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.CreateController#createNode(String)  
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null  
   > 
   > Resolved Exception:  
   >  Type = null  
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null  
   > 
   > FlashMap:  
   >  Attributes = null  
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"78"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[{"mitre_id":"A","name":"test1"}],"msg":"OK","detail":""}  
   >  Forwarded URL = null  
   >  Redirected URL = null  
   >  Cookies = []

2. 增加关系
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /create/relationship  
   >  Parameters = {start_id=[A], end_id=[B], relationship=[in]}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}  
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.CreateController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.CreateController#createRelationship(String, String, String)  
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null  
   > 
   > Resolved Exception:  
   >  Type = null  
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null  
   > 
   > FlashMap:  
   >  Attributes = null  
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"80"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[],"msg":"OK","detail":"created relationship: 'A' in 'B'."}  
   >  Forwarded URL = null  
   >  Redirected URL = null  

## 4. 修改操作

### URL

> http://localhost:8080/update
> 
> method: `GET`, `POST`

### 支持修改方式

1. 修改节点信息
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /update  
   >  Parameters = {value=[{"name":"test0","mitre_id":"A","type":"matrix"}]}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}  
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.UpdateController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.UpdateController#updateNode(String)  
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null  
   > 
   > Resolved Exception:  
   >  Type = null  
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null  
   > 
   > FlashMap:  
   >  Attributes = null  
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"78"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[{"mitre_id":"A","name":"test0"}],"msg":"OK","detail":""}  
   >  Forwarded URL = null  
   >  Redirected URL = null  
   >  Cookies = []  

## 5. 删除操作

### URL

> http://localhost:8080/delete
> 
> method: `GET`, `POST`

### 支持删除方式

1. 删除某个节点以及其所有关系  
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /delete/node  
   >  Parameters = {id=[A]}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}  
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.DeleteController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.DeleteController#deleteObjectAndRelationship(String)  
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null  
   > 
   > Resolved Exception:  
   >  Type = null  
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null  
   > 
   > FlashMap:  
   >  Attributes = null  
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"93"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[],"msg":"OK","detail":"Deleted node: 'A', deleted relationship: 'in'."}  
   >  Forwarded URL = null  
   >  Redirected URL = null  
   >  Cookies = []

2. 删除两个节点之间的关系
   
   > MockHttpServletRequest:  
   >  HTTP Method = GET  
   >  Request URI = /delete/relationship  
   >  Parameters = {start_id=[A], end_id=[B]}  
   >  Headers = []  
   >  Body = null  
   >  Session Attrs = {}  
   > 
   > Handler:  
   >  Type = uestc.zhanghanwen.ATTCK.RestWebControllers.DeleteController  
   >  Method = uestc.zhanghanwen.ATTCK.RestWebControllers.DeleteController#deleteRelationship(String, String)  
   > 
   > Async:  
   >  Async started = false  
   >  Async result = null  
   > 
   > Resolved Exception:  
   >  Type = null  
   > 
   > ModelAndView:  
   >  View name = null  
   >  View = null  
   >  Model = null  
   > 
   > FlashMap:  
   >  Attributes = null  
   > 
   > MockHttpServletResponse:  
   >  Status = 200  
   >  Error message = null  
   >  Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"71"]  
   >  Content type = text/plain;charset=UTF-8  
   >  Body = {"status":0,"result":[],"msg":"OK","detail":"Deleted relationship: in"}  
   >  Forwarded URL = null  
   >  Redirected URL = null  
   >  Cookies = []  