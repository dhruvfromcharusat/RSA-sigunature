# RSA-sigunature
I have implemented RSA for digital signature scheme using java big integer and socket programming.
Q. So what's a digital siginature?
-> simple a digital key that only authorized person can have or possess to prove it's identity over internet or over a connection.
   example. A king will have it's sigunature stamp on it's message so that everyone knows that this message has a royal sigunature on it.


For implementation of rsa for digital sigunature in client server format will be steps below.
# STEP 1
We have to compile both code simultaneously on two different CMD(comand prompts).
Run first server side code cause it should be active first to recive request form client.
# STEP 2
will display "Waiting for client in port number 8080(according to my code)" then run rsaclient and it will establish connection between server and client.
# STEP 3
Now server will ask you two prime numbers 'p' and 'q'.You have to enter prime compulsory. 
Then server will calcuate respective n(group) and totient function fi(n).
# STEP 4
It's a important step now you have to enter a public key 'e' which gives g.c.d. WRT fi, 1 or GCD(e,fi)=1. 
We can say that e should be co_prime with totient function fi.
# STEP 5
Server will compute d private key on bases of public key e entered using euclidean extended algorithm logic. 
Euclidean extended algorithm will claculate inverse of 'e' in group 'n'. 
NOTE: Every element present in the group has a unique inverse element existing. 
Now over the socket connection e and n will be shared to client but d is kept restricted to server side and will not be shared over connection.
Message will be entered by server as server have to digitally sign it.
Then encryption will be done on server side using formula (m^d)MOD n= cipher text(encryption using private key).
Now contol is in hand of client so now we have to decrypt message 'm' using public key pair (e,n).
# STEP 6
So the public key shared over connection will be used for decryption.
Formula for decryption plain text=(ciphertext^e)MOD n.
After applying decryption operation if we retrive the message it means it is been signed by server who have provide us public key pair (e,n).

# TIPS
RSA's security rely on discrete log problem that makes brute force attack very difficult. 
Use two very large prime numbers as the security of your message and your system totally depends on n. 
Larger the prime better the security.

# REFERENCE
https://en.wikipedia.org/wiki/Digital_signature

http://www.emptrust.com/blog/benefits-of-using-digital-signatures/

## Implementation screenshots 
# EXPERIMENT ONE:
Server side:
![rsa_sig_server](https://user-images.githubusercontent.com/93263533/159757107-1f1c33f4-0c54-4e27-9a3f-44c5f0ce61ab.jpg)

client side:
![rsa_sig_client](https://user-images.githubusercontent.com/93263533/159757151-f9bd7869-ecab-4b42-99ec-616d93b26080.jpg)

# EXPERIMENT TWO:
Server side:
![rsa_sig_server_2](https://user-images.githubusercontent.com/93263533/159757176-10724dde-b7c8-45a9-aebb-08ecdd86c1ec.jpg)

client side:
![rsa_sig_client_2](https://user-images.githubusercontent.com/93263533/159757207-2323c2b1-54a9-4b5c-ba15-1f22d41272a2.jpg)
