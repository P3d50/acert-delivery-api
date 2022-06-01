# acert-delivery-api


## <a href="https://acertdeliveryapi.herokuapp.com/swagger-ui.html">Swagger em deploy</a>
### url base do deploy: https://acertdeliveryapi.herokuapp.com/ 

### <a href="https://github.com/P3d50/acert-delivery-api/blob/main/delivery.postman_collection.json">Postman json para importar as requisições</a>

#### Para testar a aplicação:

1. deve ser criado um usuário no endpoint "/api/v1/user" com o payload abaixo
	 
	 
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


 





