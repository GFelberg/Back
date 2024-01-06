# Back🔙 (1.8x - 1.20x)

## English: :earth_americas:
Back is a simple plugin made for Minecraft which has the ability to teleport the player to your last location after his death

![Vídeo Back](https://github.com/GFelberg/Back/assets/41524430/8ae7425d-3dc9-4fc2-8c78-1559dca88e46)

### Commands:
    /back help - Shows the Help Page
    /back - Teleport to your last location
    /back reload - Reloads the Plugin
  
### Permissions:
    back.back : Access to the Back command
    back.bypass : Access to the Back command without cooldown
    back.admin : Access to the Admin Help Page
    back.update : Access to recieve notifications about the plugin, as new versions
    back.reload : Access to Reload the Plugin
    
### How the Plugin works?
    It's simple. When a player dies, if he has "back.back" permission, his death location will be saved. If he has "back.bypass" permission, he will be able to
    teleport into his last location without cooldown, if enabled. The cooldown is in seconds and can be customized in the config.yml
    
    When the player execute the "/back" command, he will be teleported to his last location 

### Placeholders:
    The plugin has the %back_delaytime% placeholder. It doesnt needs to have any plugin dependency.

    %back_delaytime% : Gets the remaining time for use the Back Command, if cooldown is enabled.

### ClickOption:
    ClickOption is an option displayed in the chat to the player use
    the /back command instead typing the command in chat.
    
    By default, this option is disabled, and can be enabled
    changing the status to "true" in config.yml

![1](https://github.com/GFelberg/Back/assets/41524430/482a2079-cffe-4c5c-9958-bb991328078a)
    
    By default, you can see there's a space above and under the "&c[Click here to Death Location]" here
    in config.yml.

![2](https://github.com/GFelberg/Back/assets/41524430/219d80a5-5025-424f-b89b-dc916d122019)

    However, in game, it's 2 spaces above and under
    (1 space above and under by default in game (can't be changed) + spaces in config.yml).

![3](https://github.com/GFelberg/Back/assets/41524430/c773ae54-54d7-40c0-9742-4d395795e61c)

    If you believe is a lot, you can't just remove the spaces here in the config.yml, 
    so only 1 space will be visible above and under in game.

    If you want add more information, you can change or add more lines.
    
![4](https://github.com/GFelberg/Back/assets/41524430/ca8058ed-e6cb-4646-883a-886424de50a6)

![5](https://github.com/GFelberg/Back/assets/41524430/82ba0361-fbb5-453c-84c8-4ce6a96a8423)

### World Blacklist:
    The WorldBlacklist is a method created to block the /back command to be executed. If you put a world that exists in your server 
    in the list, if a player dies and execute the command, the player will be blocked. The command will only works if the he dies 
    inside of an allowed world.

    # By default, this option will be disabled.

![WorldBlock1](https://github.com/GFelberg/Back/assets/41524430/57cd7f81-1301-4cd6-8f5d-c97eb174e540)

![WorldBlock 2](https://github.com/GFelberg/Back/assets/41524430/723948b0-4485-440d-afe1-41c8977411ae)

### Available for download at Spigot: https://www.spigotmc.org/resources/back.94702/

### Images:
![Momento da Morte](https://github.com/GFelberg/Back/assets/41524430/15258d5d-fb33-4e3b-86bd-08400d632cac)

![Teleporte com sucesso](https://github.com/GFelberg/Back/assets/41524430/afb8ec71-ec02-48c7-8f40-8af248d0aba5)

## Portuguese: <img src="https://github.com/GFelberg/Back/assets/41524430/fa9d383c-09a8-4301-ad34-90e0e6df12fe" width="30" height="30">
Back é um plugin desenvolvido para o Minecraft que possui a habilidade de teleportar o jogador para a sua última localização após a sua morte

![Vídeo Back](https://github.com/GFelberg/Back/assets/41524430/8ae7425d-3dc9-4fc2-8c78-1559dca88e46)

### Comandos:
    /back help - Mostrar a Página de Ajuda
    /back - Teleportar para sua última localização
    /back reload - Recarregar o Plugin
  
### Permissões:
    back.back : Acesso ao comando Back
    back.bypass : Acesso ao comando Back sem cooldown
    back.admin : Acesso para a Página de Ajuda dos Administradores
    back.reload : Acesso para Recarregar o Plugin
  
### Como o Plugin funciona?
    É bem simples. Quando o jogador morre, se o mesmo apresentar a permissão "back.back", sua localização de morte será salva. Se ele possuir a permissão "back.bypass", ele poderá
    teleportar para sua última localização sem esperar pelo cooldown, se ativado. O cooldown é em segundos e pode ser customizado na config.yml
    
    Quando o jogador executar o comando "/back", ele será teleportado para sua última localização

### Placeholders:
    O plugin possui a placeholder %back_delaytime%. Não é necessário nenhuma dependência de plugin externo.

    %back_delaytime% : Recupera o tempo restante para utilizar o comando Back, se o cooldown estiver ligado.

### ClickOption:
    ClickOption é uma opção disponibilizada no chat ao jogador utilizar o comando /back por meio de um 
    clique com o mouse ao invés de digitar no chat.
    
    Por padrão, essa opção está desativada, e pode ser ativada trocando o status para "true" na config.yml

![1](https://github.com/GFelberg/Back/assets/41524430/482a2079-cffe-4c5c-9958-bb991328078a)
    
    Por padrão, você pode observar um espaço acima e abaixo da mensagem "&c[Click here to Death Location]" aqui
    na config.yml.

![2](https://github.com/GFelberg/Back/assets/41524430/219d80a5-5025-424f-b89b-dc916d122019)

    Contudo, no jogo, são 2 espaços acima e abaixo
    (1 espaço acima e abaixo por padrão no jogo (não pode ser alterado) + espaços naconfig.yml).

![3](https://github.com/GFelberg/Back/assets/41524430/c773ae54-54d7-40c0-9742-4d395795e61c)

    Se você acredita que seja muito, você pode só remover os espaços aqui na config.yml, 
    então somente 1 espaço será visível acima e abaixo no jogo.

    Se você deseja adicionar mais informações, você pode alterar ou adicionar mais linhas.
    
![4](https://github.com/GFelberg/Back/assets/41524430/ca8058ed-e6cb-4646-883a-886424de50a6)

![5](https://github.com/GFelberg/Back/assets/41524430/82ba0361-fbb5-453c-84c8-4ce6a96a8423)

### World Blacklist:
    O World Blacklist é um método de você bloquear o jogador de usar o comando /back. Caso o jogador morra em
    um mundo no qual esteja dentro da lista de mundos bloqueados, o jogador será bloqueado de retornar ao mundo.

    Caso ocorra uma morte em um mundo que seja permitido, o comando irá funcionar normalmente.

    Por padrão, essa configuração está desativada.
    
![WorldBlock1](https://github.com/GFelberg/Back/assets/41524430/57cd7f81-1301-4cd6-8f5d-c97eb174e540)

![WorldBlock 2](https://github.com/GFelberg/Back/assets/41524430/723948b0-4485-440d-afe1-41c8977411ae)

### Disponível para download no Spigot: https://www.spigotmc.org/resources/back.94702/

### Imagens:
![Momento da Morte](https://github.com/GFelberg/Back/assets/41524430/15258d5d-fb33-4e3b-86bd-08400d632cac)

![Teleporte com sucesso](https://github.com/GFelberg/Back/assets/41524430/afb8ec71-ec02-48c7-8f40-8af248d0aba5)
