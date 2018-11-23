<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Submit your question</title>
<style>
	fieldset {
		background-color: #f1f1f1;
		border: none;
		border-radius: 2px;
		margin-bottom: 12px;
		overflow: hidden;
		padding: 0 .625em;
	}
	
	label {
		cursor: pointer;
		display: inline-block;
		padding: 3px 6px;
		text-align: right;
		width: 150px;
		vertical-align: top;
	}
	
	input {
		font-size: inherit;
	}
</style>
</head>

<body>
	<!-- my implementation -->
	<form action="submitQuestion" method="post">
		<fieldset>
			<p>
				<label for="body">我的问题是(必填):</label>
				<textarea id="body" name="body" cols="40"
					rows="8" align="left"></textarea>
			</p>
			
			<p>
				<label for="answer">我知道答案</label>
				<textarea id="answer" name="answer" cols="40"
					rows="5" align="left"></textarea>
			</p>
			
			<p>
				<label for="submitter">我的昵称:</label> <input type="text"
					id="submitter" name="submitter" align="left">
			</p>
			
			<p>
				<label for="language">编程语言:</label> <input type="text"
					id="language" name="language" align="left" value="java">
			</p>
			
			<p>
				<label for="sort">分类</label> <input type="text"
					id="sort" name="sort" align="left" value="数据结构">
			</p>
			
			<p>
				<label for="company">公司出题</label> <input type="text"
					id="company" name="company" align="left" value="网络收集学习用">
			</p>
			
			<p>
				<input type="submit">
			</p>
		</fieldset>
	</form>

</body>
</html>