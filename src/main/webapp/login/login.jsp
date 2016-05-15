<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>
<body>
	<form method="post" action="j_security_check">
		<table>
			<tr>
				<td>Login</td>
				<td><input type="text" name="j_username" id="j_username" value="gustavo" /></td>
			</tr>
			<tr>
				<td>Senha</td>
				<td><input type="password" name="j_password" id="j_password" value="123" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<button type="submit">
						<span>Entrar</span>
					</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
