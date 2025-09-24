#Código padrão em python para enviar e-mails

import smtplib
import os
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

#Obtendo o e-mail do destinatário da variável de ambiente no GitHub
email_destinatario = os.getenv('EMAIL_DESTINATARIO')

#Configurações de envio (remetente do e-mail)
email_remetente = 'g.lopes@gec.inatel.br'  #O remetente do e-mail
senha_remetente = 'Mat240#@'  #A senha do remetente

#Corpo do e-mail
assunto = 'Notificação de Execução do Pipeline'
mensagem = 'Pipeline executado com sucesso!'

#Configuração do servidor SMTP
smtp_server = 'smtp.gmail.com'
smtp_port = 587

#Criar a mensagem
msg = MIMEMultipart()
msg['From'] = email_remetente
msg['To'] = email_destinatario
msg['Subject'] = assunto
msg.attach(MIMEText(mensagem, 'plain'))

#Enviar o e-mail
try:
    server = smtplib.SMTP(smtp_server, smtp_port)
    server.starttls()  #Iniciar a criptografia
    server.login(email_remetente, senha_remetente)
    text = msg.as_string()
    server.sendmail(email_remetente, email_destinatario, text)
    server.quit()
    print('E-mail enviado com sucesso!')
except Exception as e:
    print(f'Falha ao enviar o e-mail: {e}')
