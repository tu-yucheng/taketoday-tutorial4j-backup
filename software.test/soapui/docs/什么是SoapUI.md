API 测试是软件行业几乎每个质量分析师不可或缺的一部分。市场上有多种工具/框架可用于 API 自动化。例如，SoapUI 是市场的领导者之一，它使所有测试人员的 API 测试变得非常容易。它是一个跨平台的 API 或 Web 服务测试工具，可以对 API 或 Web 服务进行功能和非功能测试。此外，由于它是基于 java 的工具，它可以在大多数最新的操作系统上运行，包括 MAC、Windows 和 Linux。

随后，在本教程中，我们将涵盖以下主题以开始我们的 SoapUI 学习之旅：

-   什么是 SoapUI？
-   什么是网络服务？
    -   WebService 的类型。
    -   SOAP 和 REST 之间的区别。
-   SoapUI 提供了哪些不同的能力？
-   哪些SoapUI支持协议和技术？
-   SoapUI 与 SoapUI PRO 之间有什么区别？

## 什么是 SoapUI？

SoapUI是全球领先的开源测试工具，我们主要用于API测试。关于 SoapUI 的一些众所周知的事实是：

-   它由 Eviware 于 2005 年开发，后来于 2011 年被 SmartBear 收购。
-   此外，它还是一个跨平台的基于桌面的应用程序，可以运行几乎所有最新的操作系统。
-   除了功能测试，它还可以用于 API 和 Web 服务的检查、调用、监控、模拟和模拟。
-   此外，它还可以对基于 REST/WADL 和 SOAP/WSDL 的 API 进行各种测试，例如功能测试、安全测试、负载测试、合规性测试和监视测试。

SoapUI项目的总体架构如下图所示：

![SoapUI 架构](https://www.toolsqa.com/gallery/SoapUI/1.SoapUI%20Architecture.png)

让我们详细讨论所有这些部分：

-   测试配置文件：这些文件提供与测试数据、预期结果、数据库连接变量以及任何其他环境或测试特定细节相关的配置。
-   第三方 API：这些是有助于创建优化的测试自动化框架的第三方 API。例如，JExcel API 有助于与 Microsoft Excel 集成以创建数据驱动的框架。
-   Selenium：这些是 UI 自动化使用的 Selenium JAR。
-   SoapUI Runner：它运行 SoapUI 项目。此外，它是一个方便的测试自动化实用程序，因为它允许你从命令行运行测试。此外，它还充当测试自动化的触发器。
-   Groovy：该库使 SoapUI 能够为其用户提供 groovy 作为脚本语言。
-   属性：这些是保存任何动态生成的数据的属性文件。
-   测试报告：SoapUI 提供了 Junit 风格的报告。此外，它使用 Jasper 报告实用程序来报告测试结果。

在我们开始了解 SoapUI 及其功能的更多细节之前，让我们首先了解什么是 WebService 以及它与 SOAP 的关系。

## 什么是网络服务？

Web 服务是一个软件组件，它通过 Web 执行特定任务，例如获取数据/信息、发送或发布数据或更新数据。此外，Web 服务向设计独立于平台的客户端应用程序公开远程服务或可执行过程。此外，任何客户端都可以调用它，而不管其编程语言如何。Web 服务的一般通信/消费机制如下图所示：

![了解网络服务](https://www.toolsqa.com/gallery/SoapUI/2.Understanding%20Webservice.png)

让我们举个例子来理解这一点。假设有一家礼品店在线销售产品。礼品店技术团队构建了应用程序的一部分，它使用 .NET 技术堆栈保存所有产品的库存。此外，我们还有一个单独的应用程序用于支付，由 JAVA 构建。因此，礼品店应用程序和支付应用程序之间的集成将是一个挑战，因为它们构建在不同的技术堆栈上。

因此，我们可以通过公开 API 供支付网关团队集成来处理/解决此类集成问题。礼品店的库存应用程序可以使用此 API 并与支付网关交互。但是，如果此 API 部署在 Web 服务器上并通过 Internet 公开，它将充当 Web 服务。此外，Web 服务可以使用 XML 或 JSON 协议进行通信，并且所有支持这些协议的客户端都可以使用它们。

让我们进一步了解 Web 服务的工作原理：

### Web 服务如何工作？

Web 服务作为请求-响应范例工作。换句话说，有一个实体将向其特定的对应方请求某些服务，即服务提供者实体。根据请求，服务提供商将响应一条响应消息。所以这里涉及两条消息，一条是请求消息，一条是响应消息。因此，我们可以借助下图来描述这种通信：

![Web服务通信](https://www.toolsqa.com/gallery/SoapUI/3.WebService%20communication.png)

必须执行几个步骤才能进行 Web 服务调用。也就是说，要了解在代码中实现/使用 Web 服务需要做什么，请考虑以下事项：

-   首先，我们必须找到网络服务。它要求我们知道 TCP/IP 地址(或服务器名称)和我们需要连接的端口。
-   其次，我们要发送的所有数据都必须转换为数据流(使用标准位格式和字符集序列化)。
-   第三，我们需要格式化并提出具体要求。同样，服务器需要发送响应。
-   第四，所有接收到的数据都需要转换回我们的编程环境可以理解(反序列化)的对象和变量。
-   最后，所有这一切都需要安全地进行！

### 网络服务类型：

我们可以将 Webservices 主要分为两类：

-   首先，SOAP Web 服务。
-   第二，Restful Web 服务。

现在让我们详细了解它们：

#### 什么是 SOAP Web 服务？

SOAP (简单对象访问协议)是一种消息协议，允许应用程序的分布式元素之间进行通信。它的基础是将 XML 数据作为 SOAP 消息进行传输。此外，每条消息都有一个 XML 文档，其中包含需要在客户端和服务器之间进行通信的数据。此外，XML 文档的结构遵循由 SOAP 管理的特定模式。SOAP 支持通过传输协议(如 HTTP 和 SMTP)交换 XML 编码的消息。

SOAP 消息的基本结构如下所示：

![SOAP SoapUI 支持的项目类型之一](https://www.toolsqa.com/gallery/SoapUI/4.SOAP%20One%20of%20project%20type%20supported%20in%20SoapUI.png)

-   信封——它包裹了整个信息。此外，它还包含标题和正文。
-   标头——它是一个可选元素，带有附加信息，例如安全或路由。
-   body – 传达特定于应用程序的数据。这是一个强制性元素。
-   fault(可选)- 提供有关处理消息时发生的错误的信息。

SOAP 规范发布了两个版本：SOAP v1.1 和 SOAP v1.2。此外，无论 SOAP 规范版本如何，消息格式都保持不变。

示例消息的表示如下：

```java
<?xml version="1.0"?>
<soap:Envelope xmlns:soap="https://www.w3.org/2003/05/soap-envelope" xmlns:m="https://www.toolsqa.com/">
	<soap:Header> </soap:Header>
	<soap:Body>
		<m:GetBooksPrice>
		<m:BookName>SoapUIBook</m:BookName>
		</m:BookPrice>
	</soap:Body>
</soap:Envelope>
```

这个样本 SOAP XML 描述了 SOAP 消息的不同元素和结构，以及它们在 SOAP 消息中的组织方式。

#### 什么是 REST Web 服务？

REST 代表具象状态转移。在 REST 架构风格中，数据和功能充当资源，我们可以使用统一资源标识符 (URI) 进行访问。除此之外，Restful Web Service 是一种无状态的客户端-服务器架构，它使用 HTTP 方法来调用 Web 服务。此外，它没有指定要使用的任何特定协议，但在几乎所有情况下，它都优先于 HTTP/HTTPS。REST Web 服务的几个基本属性是：

-   资源标识通过统一资源标识符 (URI) 进行。
-   此外，资源通过它们的表示进行操作。
-   此外，消息是自我描述的和无状态的。
-   除了上述之外，还有多个表示接受或发送。
-   连接器(客户端、服务器、缓存、解析器、隧道)与会话无关。

现在我们说 REST 是无状态的，我们来了解一下究竟什么是无状态？

无状态协议是一种协议，在该协议中，客户端和服务器之间的每个特定通信的处理都作为一个独立的事件发生。此外，它始终与其他类似通信无关。即使多个请求的发送发生在同一个 HTTP 连接上，服务器也不会为它们通过同一个套接字到达的请求附加任何特殊含义。因此，在多个请求的持续时间内，服务器不会保留关于无状态协议中每个通信伙伴的会话信息或状态。

一个简单的 REST 通信如下所示：

![Rest WebService SoapUI 支持的项目类型之一](https://www.toolsqa.com/gallery/SoapUI/5.Rest%20WebService%20One%20of%20project%20type%20supported%20in%20SoapUI.png)

在上图中，客户端向 Web 服务器发送 HTTP 请求。类似地，网络服务器为此发送一个 HTTP 响应。对于通信和需要的操作，它可以使用多种方法，如下所示：

![Rest WebService SOAPUI 支持的项目类型之一](https://www.toolsqa.com/gallery/SoapUI/6.Rest%20WebService%20One%20of%20project%20type%20supported%20in%20OAPUI.png)

#### SOAP 和 REST 之间的区别

现在我们已经理解了 SOAP 和 REST 的基本概念，让我们来看看 SOAP 和 REST 之间的一些重要区别。

| 肥皂                                              | 休息                                          |
| ------------------------------------------------------- | --------------------------------------------------- |
| 1. Soap 的完整形式是简单对象访问协议。                  | 1. Rest是Representational State Transfer。          |
| 2. 它是一种基于XML 的消息协议。                         | 2. 它是一种架构风格的协议。                         |
| 3. 它使用WSDL 进行消费者和提供者之间的通信。            | 3. XML或JSON收发数据。                              |
| 4.通过调用RPC方法调用Service。                          | 4. 只需通过 URL 路径调用。                          |
| 5. 它不返回人类可读的结果。                             | 5. 结果是可读的，它是纯 XML 或 JSON。               |
| 6. 传输通过 HTTP、SMTP 或 FTP 协议进行。                | 6. 传输仅通过 HTTP 和 HTTPS。                       |
| 7. 性能不如Rest，因为它遵循适当的结构。但是，它更安全。 | 7. 性能比 SOAP- 更精简。此外，它的 CPU 密集度较低。 |

至此，我们应该清楚WebServices的概念了。随后，让我们进入下一节，了解 SoapUI 为不同类型的测试提供的各种功能：

### SoapUI 提供了哪些不同的功能？

SoapUI 团队的主要目标是通过单一测试环境为用户提供功能和非功能测试实用程序。基于这一愿景，它提供了适合不同类型测试的各种功能。其中很少有：

-   功能测试能力：SoapUI 提供了多种功能，使 Web 服务的功能测试变得非常容易。其中很少有：
    -   首先，它支持使用易于使用的 GUI 测试 SOAP 和 RESTful Web 服务。
    -   其次，它允许你开发数据驱动的测试并支持 HTTP、HTTPS 传输以及 JMS。
    -   第三，SoapUI 支持 WS-Security、WS-Addressing 等 Web 服务规范。
    -   第四，SoapUI 具有多环境设置能力。换句话说，QA、Dev、Prod 环境之间的切换很容易。
    -   第五，SoapUI 允许高级脚本(测试人员也可以根据场景开发自定义代码)。
    -   最后，SoapUI 的关键特性之一是命令行支持，它允许与 CI/CD 管道轻松集成。反过来，这在快节奏的环境中是一个重要的问题。
-   服务模拟功能：当你与分布式团队合作或使用第三方 API 时，我们会遇到多种情况，服务提供者尚未准备好，但消费者愿意。因此，为了应对此类挑战，SoapUI 提供了在实现之前模拟依赖服务和模拟 Web 服务的功能。因此，它允许你在不等待提供者实现的情况下测试消费者组件。
-   脚本功能：SoapUI 支持 Groovy 和 JavaScript，我们可以使用它们编写测试场景的脚本。
-   安全测试能力：SoapUI 具备执行完整漏洞扫描的能力。此外，它使用 OWASP 标准来检查被测应用程序中的漏洞。
-   性能测试能力：用户可以使用 SoapUI 快速生成性能和负载测试。此外，它可以使用多个 Load UI 代理来分发负载测试。

### SoapUI 支持哪些协议和技术？

SoapUI 支持广泛的协议和技术，如下所示：

![SoapUI 功能](https://www.toolsqa.com/gallery/SoapUI/7.SoapUI%20Capabilities.png)

在哪里，

-   SOAP——简单对象访问协议
-   WSDL——网络服务定义语言
-   REST——具象状态转移
-   HTTP——超文本传输协议
-   HTTPS——安全的超文本传输协议
-   AMF – 动作消息格式
-   JDBC——Java 数据库连接
-   JMS——Java 消息服务

### SoapUI 与 SoapUI PRO 之间有什么区别？

正如我们所知，有两个版本的 SoapUI。一个是 SoapUI (开源版本)，另一个是 SoapUI PRO。让我们从各个方面了解这两者之间的区别：

#### SoapUI 和 SoapUI Pro 提供的功能测试功能比较：

下表显示了开源 SoapUI 和 SoapUI pro 中可用的不同功能。

| 特征      | 肥皂用户界面 | 专业版 |
| --------------- | ------------------ | ------------ |
| WSDL 覆盖范围   | 不                 | 是的         |
| 请求/响应覆盖率 | 不                 | 是的         |
| 消息断言        | 是的               | 是的         |
| 测试重构        | 不                 | 是的         |
| 运行多个测试    | 是的               | 是的         |
| 源驱动测试      | 不                 | 是的         |
| 脚本库          | 不                 | 是的         |
| 单位申报        | 不                 | 是的         |
| 手动测试步骤    | 是的               | 是的         |

#### SoapUI 和 SoapUI Pro 提供的安全测试功能比较：

该表帮助我们了解 SoapUI 在启用安全测试的功能方面与 SoapUI Pro 有何不同。

| 特征 | 肥皂用户界面 | 专业版 |
| ---------- | ------------------ | ------------ |
| 边界扫描   | 是的               | 是的         |
| 无效类型   | 是的               | 是的         |
| SQL注入    | 是的               | 是的         |
| XPath注入  | 是的               | 是的         |
| XML 炸弹   | 是的               | 是的         |
| 模糊扫描   | 是的               | 是的         |
| 跨站脚本   | 是的               | 是的         |
| 可配置扫描 | 是的               | 是的         |
| 报告       | 不                 | 是的         |

#### SoapUI 和 SoapUI Pro 提供的负载测试功能比较：

下表有助于识别两个版本的 SoapUI 在启用负载测试的功能方面的差异：

| 特征                 | 肥皂用户界面 | 专业版 |
| -------------------------- | ------------------ | ------------ |
| 来自功能测试的快速负载测试 | 是的               | 是的         |
| 可配置的负载策略           | 是的               | 是的         |
| 负载测试断言               | 是的               | 是的         |
| 实时统计                   | 是的               | 是的         |
| 性能监控                   | 是的               | 是的         |
| 统计输出                   | 是的               | 是的         |
| 使用 Groovy 脚本设置/拆卸  | 是的               | 是的         |
| loadUI 集成                | 是的               | 是的         |
| 报告                       | 不                 | 是的         |

#### SoapUI和SoapUI Pro生成的报告对比：

|                | 肥皂用户界面 | 专业版 |
| -------------- | ------------------ | ------------ |
| JUnit 报告     | 是的               | 是的         |
| 报表数据导出   | 不                 | 是的         |
| WSDL HTML 报告 | 是的               | 是的         |
| WSDL 覆盖范围  | 不                 | 是的         |
| 测试套件覆盖率 | 不                 | 是的         |
| 测试用例覆盖率 | 不                 | 是的         |
| 断言覆盖率     | 不                 | 是的         |
| 留言录音覆盖率 | 不                 | 是的         |
| JUnit 报告     | 不                 | 是的         |

## 关键要点

-   SoapUI 是一个独立于平台的 WebService 自动化工具，它提供功能和非功能自动化的能力。
-   此外，SoapUI 支持对 SOAP 和 REST API 进行测试。
-   此外，SoapUI 支持所有主要协议，如 SOAP、REST、JMS、JDBC、WSDL、JSON 和 XML 等。
-   除此之外，除了开源版本，SoapUI 还提供了一个名为 SoapUI Pro 的许可版本，它提供了 API 自动化的扩展功能。

最后，在深入探讨 SOAP 的复杂性和用法之前，让我们转到下一篇文章[“SoapUI 安装和配置”](https://www.toolsqa.com/soapui/install-soapui/)。随后，它将帮助我们了解如何在各种支持的平台上安装 SoapUI，以及用户可以使用哪些配置来开始 SoapUI 之旅。