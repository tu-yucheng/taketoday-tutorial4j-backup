package cn.tuyucheng.taketoday.persistence;

import cn.tuyucheng.taketoday.web.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FooRepository extends JpaRepository<Foo, Long>, JpaSpecificationExecutor<Foo> {
}