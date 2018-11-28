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
	<form action="submitQuestion" method="get" accept-charset="UTF-8">
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
				<input type="submit" value="提交">
			</p>
		</fieldset>
	</form>

</body>
</html>