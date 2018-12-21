# Web Project Generator
### 什么是Web Project Generator？
###### Web Project Generator适用于多种框架的Web项目代码生成，可根据项目团队的代码风格自定义代码模板
###### 默认模板仅支持Spring MVC + Spring + Mybatis
### 模板格式
###### Web Project Generator标准模板应符合XML编写规范，示例：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE template>
<!-- 标准模板的根标签应为generator -->
<generator>
	<!-- 模板格式，默认为default，即标准的freemarker文档格式 -->
	<format>default</format>
	<!-- 模板内容，引擎将根据模板内容编译成目标文档 -->
	<source>package ${groupId}.dao.${table.nameElements[0]};

import java.util.List;

import org.springframework.stereotype.Repository;

import ${groupId}.entity.${table.nameElements[0]}.${table.className};

/**
 * 数据访问对象
 * 
 * @author Web Project Generator
 * 
 */
@Repository
public interface ${table.className}Dao {
		
	/**
	 * QBE查询
	 */
	List&lt;${table.className}> queryList(${table.className} ${table.objectName});

}
	</source>
	<!-- 此处为输出文件的信息 -->
	<outFile>
		<!-- 输出路径 -->
		<path>${groupId?replace('.', '/')}/dao/${table.nameElements[0]}/</path>
		<!-- 文件名 -->
		<name>
			${table.className}Dao.java
		</name>
	</outFile>
</generator>
```
### 模板内置的对象
 - table 数据表
   + table.name 表名
   + table.className 表对应的POJO类名
   + table.objectName 表对应的POJO对象名
   + table.nameElements 表名中单词列表
   + table.comment 表注释
   + table.dbFields 表中的column对象列表
     - table.dbFields[n].column 表中第n列的列名
     - table.dbFields[n].property 表中第n列对应的属性名
     - table.dbFields[n].type 表中第n列在数据库中的类型
     - table.dbFields[n].javaType 表中第n列对应属性的类型
     - table.dbFields[n].length 表中第n列在数据库中定义的长度
     - table.dbFields[n].comment 表中第n列的注释
### 我该如何生成项目
###### 目前仅支持通过运行ProjectGenerator.java中的main方法启动生成器
###### 我会在以后的版本中为该项目实现更多支持