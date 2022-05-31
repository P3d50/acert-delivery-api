# acert-delivery-api

Após clonar o projeto executar os passos abaixo


caso não tenha o mvn installado, instalar com o comando abaixo. 
	LINUX 
  
    > sudo apt install maven
  
windows Refrência:https://maven.apache.org/install.html


executar o comando abaixo no diretório raiz do projeto

    > mvn clean install 

será gerado o arquivo jar da aplicação na pasta target

Para rodar a aplicação, dentro da pasta target executar o comando abaixo, se atentar ao nome do arquivo .jar gerado

    > java -jar delivery-0.0.1-SNAPSHOT.jar
	
	
A aplicação esta configurada para rodar na porta padrão 8080


### <a href="http://localhost:8080/swagger-ui.html#/">Swagger</a>

### <a href="https://github.com/P3d50/acert-delivery-api/blob/main/delivery.postman_collection.json">Postman export json</a>

#### Para testar a aplicação:

1. após ainstalação deve ser criado um usuário no endpoint "/api/v1/user" com o payload abaixo
	 
	 
	{
		"password": "string",
		"username": "string"
	}
	
	
2.no endpoint "/api/v1/user/auth" autenticar o usuário criado no passo 1,

	{ 
	 	"password": "string",
  		"username": "string"
	}
	
será retornado um token JWT 
	 
	 {
    		"username": "appUser",
    		"token": "eyJhbUxMiJ9.eyJOTYxMDA1fQ.5LYYXITfsQ1QRowuc0L4PvqCTUcWzJQjMPO287eobf7TSbPG1OYyx37w"
	}
	
#### após autenticado é possível fazer:
cadastro, alteração, deleção e consulta de clientes.
cadastro, alteração, deleção e consulta de pedidos. 
Um pedido obrigatoriamente precisa ter um cliente(faz referência ao id do cliente) e um cliente pode ter vários pedidos.
cadastro, alteração, deleção e consulta de entregas. Uma entrega obrigatoriamente necessita estar vinculada a um pedido(faz referência ao id dopedido).

 





