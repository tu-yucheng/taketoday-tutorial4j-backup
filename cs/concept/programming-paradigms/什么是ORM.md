## 一、简介

在本教程中，我们将探索什么是对象关系映射工具、它们的作用、它们的工作原理，以及为什么在大多数情况下我们应该使用一个工具。目前，到目前为止，最常用的编程范式是[面向对象编程](https://www.baeldung.com/cs/oop-vs-functional)(OOP)。它允许我们在设计层面将[现实世界的](https://www.baeldung.com/cs/oop-modeling-real-world)实体表示为[对象](https://www.baeldung.com/cs/class-object-differences)及其关系。对象包含数据(属性)和代码(方法)。一旦我们掌握了它，它真的很棒。

它带来了很多不错的功能来帮助我们编码。例如，我们可以创建共享来自更通用类的基本定义的对象类。因此我们可以只改变不同的方法和属性，写更少的代码。然而，一个问题是如何使用非面向对象的数据库(例如关系数据库)来持久保存对象的数据。

在实践中，我们会创建一组模拟对象属性和类之间关系的表，这个过程称为对象关系映射。说起来容易做起来难。

随着系统的发展，对类及其关系的更改必须伴随着对映射的调整。因此，我们必须审查数据库结构以及所有相关的数据访问代码。大中型系统可能包含成百上千个类。这就是对象关系映射工具应运而生的时候。

## 2. 什么是 ORM 工具？

如今，设计和部署信息系统确实是一项艰巨的任务。在大多数情况下，我们必须了解多种编程语言和框架。每个应用层都有自己的技术栈。而且，虽然有些发展非常快，但有些使用的是几十年前的技术。例如，在大多数公司的老式关系数据库中，数据持久性标准仍然是正确的。

嗯，对于大多数商业应用程序来说，关系数据库是可靠的，足够快的。因此，它们的使用非常广泛，并且拥有强大的社区支持。在此体系结构中，逻辑实体列在表中，这些表将每个实例的属性保存在它们的列中。此外，这些表可以与其他表相关联，以对实体之间的逻辑关系进行建模。他们还使用一种标准化的方式来添加、访问、更新和删除数据，即结构化查询语言 (SQL)。

对象关系映射工具 ORM 是一个框架，可以帮助和简化两种范例之间的转换：对象和关系数据库表。它可以使用类定义(模型)来创建、维护和提供对对象数据及其数据库持久性的完全访问。该图显示了 ORM 如何将一组类映射到关系表中的摘录：

 

[![对象关系管理](https://www.baeldung.com/wp-content/uploads/sites/4/2023/02/ORM.svg)](https://www.baeldung.com/wp-content/uploads/sites/4/2023/02/ORM.svg)

## 三、优点

第一个好处是我们可以使用后端自己的语言来编码所有的数据访问。例如，要收集人员列表，我们需要编写一个类似于以下的函数：

```

```

现在，同样的例子，但是使用了 ORM 工具：

```

```

第二个摘录更易于编码和阅读。它引用定义属性和业务相关方法的类(即Person )。处理对象关系映射的类称为模型。这些类用作一个模板，一个来自 ORM 模型类库的类。这样，它继承了从数据库中创建、检索、更新和删除所需的所有方法。

模型与应用程序的其余部分弱绑定。这意味着更改它们不会对其他组件造成太大伤害。此外，表关系在模型类中表示。这样，外部表键将导致指向引用对象的类属性。

此外，ORM 可以跟踪我们自己的模型类中的任何更改，将它们同步到实际的数据库结构，这个过程称为迁移。此外，它抽象了数据库后端，因此我们可以轻松地切换数据库或编写兼容大量数据库供应商和版本的应用程序。

最后，ORM 具有实用功能来帮助备份、恢复或重新创建数据库。这对于编写测试函数以及其他用例非常有用。

## 3.缺点

另一方面，要正确使用 ORM，我们必须经历其陡峭的学习曲线。即使它使用我们的代码所基于的相同语言，ORM 也有自己的使用语义和语法。

ORM 最终负责将我们的代码翻译成 SQL。然而，即使在大多数情况下它都做得很好，但在复杂查询上它无法与编写良好的 SQL 竞争。

此外，即使尽最大努力创建尽可能最好的 SQL 子句，也可能出错。特别是因为有时很难了解 ORM 在幕后做了什么。

## 4. 我们应该使用 ORM 吗？

简而言之，是的，我们应该尽可能使用 ORM。对于大多数应用程序来说，优点远远超过缺点。此外，如果需要，我们仍然可以使用原始 SQL 子句，尽管非常不建议这样做。任何主要框架都至少有一个可用的 ORM 库，例如：

-   C# 和 .Net：[NHibernate](https://en.wikipedia.org/wiki/NHibernate) 或 [实体框架](https://en.wikipedia.org/wiki/Entity_Framework)
-   Go 语言：[GORM](https://gorm.io/index.html)
-   Java： [休眠](https://www.baeldung.com/learn-jpa-hibernate)
-   NodeJS：[TypeORM](https://typeorm.io/#/)、[Sequelize](https://sequelize.org/)或[KnexJS](http://knexjs.org/)
-   PHP：[Propel](https://en.wikipedia.org/wiki/Propel_(PHP)) 或 [Doctrine](https://en.wikipedia.org/wiki/Doctrine_(PHP))([Symfony](https://symfony.com/)的 ORM)
-   Python：[Django](https://docs.djangoproject.com/en/4.1/topics/db/)(具有出色 ORM 的 Web 框架)或[SQLAlchemy](https://en.wikipedia.org/wiki/SQLAlchemy)

## 5.我应该如何使用ORM？

首先，我们必须始终阅读文档并遵循它的核心。熟悉 ORM 需要一些时间，并且为了避免它的许多陷阱，我们必须熟悉它的最佳实践。ORM 过去常常有自己的措施来避免[SQL 注入攻击](https://www.baeldung.com/cs/sql-injection)等问题。但是，糟糕的编码会使这些措施无效。

尽管 ORM 抽象了数据库，但它的一些局限性可能会显示出来。例如，批量插入和更新。当我们直接处理 SQL 到批量操作时，性能是一个问题，因此我们必须使用适当的技术。ORM 也是如此，在处理高交易量时，我们必须寻找最佳的数据访问方法。

此外，我们不能跳过故障排除、[调试](https://www.baeldung.com/cs/bugs-debugging)和分析文档部分，这些部分是分析应用程序行为和性能的宝贵工具。

## 六，总结

具有数百或数千个类的真实应用程序可能是一场噩梦。编写 SQL 代码来创建、读取、更新和删除每个类可能会减轻负担。而且，如果我们更改一个类，我们必须审查所有相关的 SQL 代码。

这就是为什么对于大型应用程序，必须使用 ORM 工具。在本教程中，我们讨论了 ORM 的工作原理以及我们如何从中受益。