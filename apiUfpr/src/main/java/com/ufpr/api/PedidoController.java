package com.ufpr.api;

import java.util.HashMap;
import java.util.List;

import com.ufpr.domain.Cliente;
import com.ufpr.domain.ClienteService;
import com.ufpr.domain.pedido.PedidoDTO;
import com.ufpr.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.domain.itemDoPedido.ItemDoPedidoService;
import com.ufpr.domain.pedido.Pedido;
import com.ufpr.domain.pedido.PedidoService;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private ClienteService serviceCli;

    @Autowired
    private ClienteService clienteService;
    private ItemDoPedidoService servicePedido;

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<HashMap<String, String>> get() {
        HashMap<String, String> map = new HashMap<>();

        Iterable<PedidoDTO> pedidos = service.getPedido();
        if (pedidos != null) {
            return new ResponseEntity(pedidos, HttpStatus.OK);
        } else {
            map.put(Strings.ERRO, Strings.ERRO_BUSCAR_PEDIDOS);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> get(@PathVariable("id") Long id) {

        try{
            PedidoDTO pedido = service.getPedidoPorId(id);
            if(pedido.getIdPedido()!=null)
                return new ResponseEntity(pedido, HttpStatus.OK);
            else{
                return disparaPedidoNaoEncontrado();
            }

        }catch (Exception ex){
            return disparaPedidoNaoEncontrado();
        }
    }
    
    @CrossOrigin
    @GetMapping("/ClienteCpf/{cpf}")
    public ResponseEntity<HashMap<String, String>> get(@PathVariable("cpf") String cpf) {
    	
    	HashMap<String, String> map = new HashMap<>();
    	Cliente cliente;
    	String IdCliente = "";
    	
    	List<Cliente> clientes = serviceCli.getClientesByCpf(cpf);
    	
    	if (clientes.isEmpty())
    	{
    		map.put(Strings.ERRO, Strings.ERRO_CLIENTE_NAO_ENCONTRADO);
    		return new ResponseEntity(map, HttpStatus.OK);
    	}else {
    		
    		for (int i = 0; i < clientes.size(); i++) {
    			cliente = clientes.get(i);
				IdCliente = cliente.getId().toString();
			}
    		
    		List<PedidoDTO> pedidos = service.getPedidoPorIdCliente(IdCliente);

    		if(pedidos.isEmpty())
    		{
        		map.put(Strings.STATUS, Strings.SUCESSO_LISTA_PEDIDO_VAZIA);
        		return new ResponseEntity(map, HttpStatus.OK);
    		}else {
        		return new ResponseEntity(pedidos, HttpStatus.OK);
    		}
       }
    }

    private ResponseEntity<HashMap<String, String>> disparaPedidoNaoEncontrado() {
        HashMap<String, String> map = new HashMap<>();

        map.put(Strings.ERRO, Strings.ERRO_BUSCAR_PEDIDO_ID);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<HashMap<String, String>> post(@RequestBody Pedido itemPedido) {

        HashMap<String, String> map = new HashMap<>();

        try {
            service.save(itemPedido);
            map.put(Strings.STATUS, Strings.SUCESSO_INCLUIR_PEDIDO);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put(Strings.ERRO, Strings.ERRO_INCLUIR_PEDIDO);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
    }
}
