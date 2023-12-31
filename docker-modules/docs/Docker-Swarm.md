在我们之前的文章中，我们讨论了***Docker 的重要概念以及*** 有助于同时运行多个容器的[***Docker compose概念。***](https://www.toolsqa.com/docker/docker-compose/)本文将介绍另一个概念“Docker Swarm”，它使*Docker 跨多个节点工作，*并允许这些节点共享容器。随后，在本文中，我们将讨论***Docker Swarm***的概述，主题如下：

-   什么是 Docker 群？
    -   *为什么以及何时使用 Docker Swarm？*
    -   *Docker Swarm 是如何工作的？*
    -   *Swarm 模式的关键概念是什么？*
-   *设置 Docker Swarm 集群*
-   *Swarm 模式 CLI 命令*
-   *Kubernetes 与 Docker Swarm*

## 什么是 Docker 群？

*Docker Swarm*是运行[***Docker***](https://www.toolsqa.com/docker/introduction-to-docker-and-docker-architecture/)应用程序并将其配置为加入集群的机器组/集群（*物理或虚拟）。*一旦一组运行 Docker 应用程序的机器聚集在一起，我们称之为“ ***Swarm 模式***”。所以在 Swarm 模式下，我们仍然可以执行[***Docker 命令***](https://www.toolsqa.com/docker/docker-commands/)，唯一不同的是现在集群中的机器将执行它们。此外，我们有一个名为“ ***Swarm Manager*** ”的实体，这是一台控制集群活动的机器。加入集群的其他机器被称为“***节点***”。

在较高的层次上，我们可以将*Docker Swarm*视为一种编排管理工具。*Docker Swarm*在 Docker 应用程序上运行，帮助开发人员/最终用户创建和部署 Docker 节点集群。此外，应用程序的高可用性是 Docker swarm 提供的主要优势之一。例如，我们可以调度应用程序任务，使 Swarm 集群中的每台机器各有一个任务。此外，它有助于有效分配任务并减少任务的周转时间，从而提高吞吐量。![Docker Swarm 镜像](https://www.toolsqa.com/gallery/Docker/1-Docker%20Swarm%20image.png)

上图显示了带有多个[***docker 容器***](https://www.toolsqa.com/docker/docker-networking/)*的Docker Swarm 模式*。在*Docker Swarm*模式下，每个节点都是一个 Docker 守护进程（*用于在系统上运行 Docker*），所有这些 Docker 守护进程都使用 Docker API 相互交互。此外，我们可以将每个容器部署在 Swarm 中，同一集群的节点可以访问它们。

任何给定的 Docker 主机都可以是管理器节点、工作节点，或执行这两种角色。*Docker Swarm*中的***任务***是一个正在运行的容器，Swarm 管理器对其进行管理。它不是一个独立的容器。

下面给出了*Docker Swarm*的一些基本特性：

-   ***分散访问**：使用 swarm，团队可以轻松管理和访问环境。*
-   ***高安全性**：集群节点之间的通信非常安全。*
-   ***自动负载平衡**：Swarm 在环境中具有自动负载平衡。此外，我们可以编写此功能的脚本来构建 Swarm 环境。*
-   ***高可扩展性**：负载平衡功能使我们能够创建高度可扩展的应用程序。*
-   ***回滚任务**：在 Swarm 模式下，我们可以将环境回滚到以前的安全环境。*
-   ***高可用性应用程序**：Swarm 集群使应用程序具有高可用性。*
-   ***Desired State Reconciliation**：由于集群的状态受到持续监控，因此集群的实际状态和期望状态之间的任何差异都会立即协调。例如，假设 Docker 主机的任何一个副本在集群中崩溃。在这种情况下，Swarm 管理器会为副本崩溃的工作节点分配一个正在运行且可用的新副本。*
-   ***滚动更新**：我们还可以在推出时以增量方式将服务更新应用于集群中的节点。如果这些更新出现任何问题，我们可以方便地回滚到以前版本的服务。*

### ***为什么以及何时使用 Docker Swarm？***

虽然我们已经了解了*Swarm 模式*的概述及其工作原理以及关键概念，但问题仍然是为什么我们必须使用*Docker Swarm*？让我们简要讨论一下*Docker Swarm*的用例。

假设我们有一个应用程序服务器可以为 ' *n* ' 个客户端提供服务。如果我们需要服务两倍数量的客户端，即 2n，我们可能必须增加服务器上的资源或创建应用程序服务器的另一个实例并将其与第一台服务器进行负载平衡。

随着要服务的客户端数量的增加，我们通过调试额外的应用程序服务器来增加集群。大多数情况下，我们是手动完成的，但一些服务器具有内置的集群功能，以帮助加速和自动化集群中其他节点的调试。Docker 来到这里并提供了一种通过创建所需数量的应用程序容器来创建集群节点“***群”的方法。***Docker 允许我们使用很少的命令在任意数量的主机上部署任意数量的应用程序服务器。这样，我们可以快速扩展应用程序。

在我们考虑迁移到*Docker Swarm*之前，最好先回答以下问题，以帮助我们决定到底应该在哪里使用 Swarm？

-   ***我们需要扩展到一台主机之外吗？**: 使用多个服务器总是比使用单个服务器复杂。那么具有更高级功能的服务器是否可以解决我们的问题，还是必须有多个服务器？*
-   ***我们需要高可用性吗？**：决定我们是否需要高可用性，或者我们可以不用它。*
-   ***使用的容器是否真正无状态？**: Swarm 容器不应使用任何卷。但是，尽管这在理论上是可能的，但实际上可能是不可能的。一种选择是将媒体文件和数据库移出 swarm，这样我们几乎可以拥有无状态容器。*
-   ***我们是否需要一个日志聚合系统，比如ELK stack？**：日志聚合系统适用于所有分布式系统。*
-   ***我们真的需要高级功能吗？**：我们应该决定是否要在我们的应用程序环境中使用 Docker Swarm 的功能或更高级的功能，如 Kubernetes。*

当我们对以上所有问题都有肯定的答案时，我们就可以决定我们的应用环境是否需要使用Docker Swarm。接下来，我们通过一些 Swarm 模式命令。

### ***Docker Swarm 是如何工作的？***

在 Swarm 模式下，“*服务*”用于启动容器。此外，我们可以将服务定义为属于同一镜像的一组容器，以扩展应用程序。注意，在以Docker Swarm方式部署服务之前，我们至少要部署一个节点。

工作节点使用*API* over *HTTP*与管理节点通信。在Swarm模式下，同一集群的任意节点都可以部署和访问服务。此外，我们必须指定在创建服务时使用哪个容器镜像。此外，我们可以通过两种方式使用服务：***复制***服务和***全局***服务。在***复制服务***模型中，集群管理器根据服务规模将特定数量的复制任务分配给节点。类似地，对于***全局服务***，Docker Swarm 为集群中存在的每个节点上的服务运行一个任务。

我们在 Docker Swarm 中有三种类型的节点：

-   ***管理器节点**：管理器节点管理成员资格和委托。此外，它还处理集群管理任务。*
-   ***工作节点：工作节点**接收并执行来自管理节点的任务和服务。*
-   ***领导节点**：领导节点是主要的管理节点。此外，它还为 swarm 集群做出所有 swarm 管理和编排决策。*

这里的*任务*是一个正在运行的容器，它是 swarm 服务的一部分。与独立容器相反，群管理器管理任务。随后，下图展示了一个典型的*Docker Swarm 集群*。![Docker Swarm 集群](https://www.toolsqa.com/gallery/Docker/2-Docker%20Swarm%20Cluster.png)

上图显示了 Swarm 集群中的一个管理节点和一个工作节点。管理节点知道集群中工作节点的状态。此外，工作节点检查任务并从管理节点接受它们。每个工作节点都有一个关联的代理，其工作是向管理器节点报告节点任务的状态。此外，它有助于管理器节点维护集群的所需状态。

*那么任务和服务是一回事吗？答案是**否定***的。

任务是要完成的工作，服务是对该任务或状态的描述。使用 Docker，我们可以创建可以启动任务的服务。一旦将任务分配给特定节点，我们就无法将其分配给另一个节点。此外，我们可以在一个 Swarm 集群中有多个管理节点，但其他管理节点只会选举一个主管理节点。

如上图所示，Docker Swarm 环境有一个 API，它允许我们通过为每个服务创建任务来进行编排。每个服务都是基于命令行界面创建的。此外，工作通过任务的 IP 地址分配给任务（*上图中的任务分配*）。调度程序和调度程序负责分配和指示工作节点运行任务。Worker 节点连接到管理器以检查新任务。此过程的最后阶段是工作节点执行管理节点分配的任务。

它简要总结了 Docker Swarm 的工作。随后，现在让我们了解一下Docker Swarm模式中的一些关键概念。

### ***Swarm 模式的关键概念是什么？***

以下是与 Docker Swarm 相关的关键概念。

#### ***节点***

***节点***是参与 swarm 集群的 Docker 引擎实例。 我们也将其称为*Docker 节点*。一个或多个节点可以在单个物理机或云服务器上执行。尽管如此，在实际的生产集群环境中，我们将 Docker 节点分布在多个物理和云机器上。如上所述，我们在 Docker Swarm 中有两种类型的节点，即管理节点和工作节点。

当应用程序部署到 swarm 时，我们将服务定义提交给***管理器节点***。管理器节点然后将工作单元或任务分派给工作节点。管理器节点还负责编排和集群管理功能，以帮助维持群的所需状态。

***工作节点***负责执行从管理节点分派给它们的任务。代理在每个工作节点上运行，并向管理器节点报告其分配的任务。它帮助管理节点维护每个工作节点的所需状态。

#### ***服务和任务***

服务是要在管理器或工作节点上执行/运行的任务的定义。服务是集群系统的中心结构，是用户与集群交互的主根。当我们创建服务时，我们必须指定要使用哪个容器镜像以及在运行的容器中执行哪些命令。我们已经在 Docker Swarm 的工作中讨论了上面的服务。

***任务***携带 Docker 容器以及在该容器内运行的命令。 任务是集群的原子调度单元。根据服务规模上设置的副本数量，管理节点将任务分配给工作节点。***请注意，一旦任务被分配到特定节点，它就不能移动到另一个节点。它只能在分配的节点上运行或失败***。

#### ***负载均衡***

swarm manager 具有***入口负载均衡***的特性。Ingress 负载均衡暴露了***Swarm 外部可用***的服务。该服务可以自动分配给 PublishedPort，或者 swarm 管理器可以将任何未使用的端口配置为该服务的 PublishedPort。***如果未指定端口，Swarm 管理器会将服务分配给 30000 - 32767 范围内的端口***。

可以通过外部组件访问集群中任一节点的 PublishedPort 上的服务。例如，云负载平衡器，无论节点当前是否正在运行服务任务。请注意，swarm 中的所有节点都将入口连接路由到正在运行的任务实例。Docker Swarm 模式有一个内部 DNS 组件，它会自动为 Swarm 集群中的每个服务分配一个 DNS 条目。Swarm 管理器然后使用内部负载平衡根据服务的 DNS 名称在集群内的服务之间分配请求。

## 设置 Docker Swarm 集群。

***现在让我们在Ubuntu 16.04***上设置一个 Docker Swarm 管理器节点和一个服务。此设置的先决条件是我们应该在主机上安装最新版本的 Docker 引擎。那么让我们从步骤开始吧。

***1. 下载并运行容器。***

首先，我们可以验证主机上的 docker 版本。然后我们使用以下命令下载容器，比如 MySQL。

```
docker pull mysql
```

此命令将下载最新版本的 MySQL 容器，如下面的输出所示。![下载容器](https://www.toolsqa.com/gallery/Docker/3-Download%20container.png)

接下来，我们使用以下命令运行此容器。

```
docker run -d -p0.0.0.0:80:80 mysql:latest
```

此命令生成以下输出。![4-运行容器.png](https://www.toolsqa.com/gallery/Docker/4-Run%20container.png)

如上面的屏幕截图所示，我们可以使用显示上述容器条目的“ *docker ps -a ”命令来验证 MySQL 容器的运行。*一旦容器开始运行，我们就可以继续创建 Docker Swarm。

***2. 使用 swarm init 命令创建 Swarm - docker***

首先，我们通过提供管理器节点的 IP 地址来创建一个 Swarm 集群。为此，发出以下命令。

```
docker swarm init --advertise-addr 192.168.2.151
```

此命令生成以下输出。![创建 Docker Swarm 管理节点](https://www.toolsqa.com/gallery/Docker/5-Create%20Docker%20Swarm%20manager%20Node.png)

从上面的输出可以看出，管理器节点已创建。

***注意：我们可以通过复制“** **swarm init** ”（上述命令）的命令并将输出粘贴到工作节点“ ***sudo Docker Swarm join --token SWMTKN-1-xxxxx*** ”来类似地创建一个工作节点。它必须在不同的主机上执行。因此，要创建一个完整的 swarm 集群，我们需要两台安装了最新版本 Docker Engine 的主机。*

完成此操作后，我们将继续并使用以下命令列出来自管理器节点的节点。

```
docker node ls
```

此命令将生成以下输出。![节点列表](https://www.toolsqa.com/gallery/Docker/6-List%20of%20nodes.png)

截至目前，我们只有管理器节点，因此上述命令仅显示管理器节点的详细信息。一旦我们创建了一个工作节点，它也会显示这些细节。

接下来，我们以 Swarm 模式启动服务。我们必须在管理器节点中进行。我们将使用以下命令部署简单服务“ *HelloWorld ”。*

```
docker service create --name HelloWorld alpine ping docker.com
```

该命令生成以下输出。![部署服务](https://www.toolsqa.com/gallery/Docker/7-Deploy%20a%20service.png)

我们可以使用“ *docker service ls* ”命令验证服务，如上面的输出所示。这样，我们在 Ubuntu 16.04 上的 swarm 集群就完成了。

有关 Docker Swarm 命令的完整列表，请参阅 [***Docker Swarm 命令***](https://docs.docker.com/engine/reference/commandline/swarm/)。

## Swarm 模式 CLI 命令

下表显示了 Swam 模式 CLI 命令。

| ***CLI 命令*** | ***描述***                                                   | ***命令用法***                                           |
| -------------- | ------------------------------------------------------------ | -------------------------------------------------------- |
| 群体初始化     | 此命令初始化一个群。执行此命令的 docker 引擎成为管理器节点。 | docker swarm init [选项]                                 |
| 群加入         | 它作为一个节点加入 swarm 集群。根据传递的令牌值（*使用 –token 标志*），节点作为管理节点或工作节点加入 | docker swarm 加入 [OPTIONS] HOST:PORT                    |
| 服务创造       | 该命令创建一个新服务                                         | docker service create [OPTIONS] IMAGE [COMMAND] [ARG...] |
| 服务检查       | 命令“ ***service inspect*** ”在 JSON 数组中显示有关指定服务的详细信息。 | docker service inspect [选项] 服务 [服务...]             |
| 服务 ls        | “ ***service ls*** ”命令列出服务。                           | 码头服务 ls [选项]                                       |
| 服务室         | 从群中移除一项或多项服务                                     | docker 服务 rm 服务 [服务...]                            |
| 服务规模       | 使用此命令，我们可以将一个或多个服务扩展或缩小到所需的数量。我们只能将此命令应用于复制服务。 | docker 服务规模 SERVICE=REPLICAS [SERVICE=REPLICAS...]   |
| 服务 ps        | 列出作为指定服务一部分的正在运行的任务。                     | docker 服务 ps [选项] 服务 [服务...]                     |
| 服务更新       | 更新指定服务                                                 | docker 服务更新 [选项] 服务                              |

***注意**：以上所有命令仅适用于 1.24 及以上版本的客户端和守护进程 API。*

## Kubernetes 与 Docker Swarm

下表显示了*Kubernetes*和*Docker Swarm*之间的显着差异。

| ***库伯内斯***                                | ***码头群***                                              |
| --------------------------------------------- | --------------------------------------------------------- |
| 很复杂。                                      | 群是一个简单的工具。                                      |
| 我们与同一个 pod 中的容器共享 Kubernetes。    | 我们可以与任何容器共享 Docker Swarm。                     |
| Kubernetes 很难设置。                         | Swarm 相对容易设置。                                      |
| 我们需要在 Kubernetes 中进行手动负载均衡。    | Swarm 具有自动负载平衡。                                  |
| 在 Kubernetes 中，容器的部署和扩展速度较慢。  | 在 Swarm 中，容器部署要快得多。                           |
| 我们应该在 Kubernetes 中切换平台时重写 YAML。 | 在Swarm中，我们可以很方便的将一个容器部署到不同的平台上。 |
| Kubernetes 包含管理这两个进程的内置工具。     | Swarm 不需要用于日志记录和监控的工具。                    |
| 它在分布式节点之间具有高可用性。              | 它通过冗余提高了应用程序的可用性。                        |

## 关键要点

-   *Docker Swarm 是一个编排工具。此外，它还有两个重要的节点，即管理器节点和工作器节点。manager节点负责Swarm集群的管理和向worker节点分发任务。工作节点检查任务然后完成它们。*
-   *除了上述之外，工作节点与代理相关联，该代理与管理节点就任务状态进行通信。从而帮助管理节点维护状态。而且，我们在上面的文章中还搭建了一个Swarm集群。*
-   *因此，它完成了讨论 Docker Swarm 简要概述的文章。*