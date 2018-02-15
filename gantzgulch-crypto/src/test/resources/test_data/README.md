dd if=/dev/urandom of=data_01.dat bs=1k count=5

openssl genrsa -out openssl_rsa_2048_A.pem

openssl sha -sha256 -sign openssl_rsa_2048_A.pem < data_01.dat > data_01.signed.openssl_rsa_2048_A.dat

