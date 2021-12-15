package com.gantzgulch.tools.crypto.x509;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.tools.crypto.pkcs10.PKCS10Reader;
import com.gantzgulch.tools.crypto.x509.impl.GGCertificateVerifierImpl;

public class GGCertificateVerifierTest {

    // Google wildcard certificate
    public static final String CERTIFICATE1 = "" + //
    "-----BEGIN CERTIFICATE-----\n" + 
    "MIINsjCCDJqgAwIBAgIRAPq8ife/MxCUCgAAAAEl/TIwDQYJKoZIhvcNAQELBQAw\n" + 
    "RjELMAkGA1UEBhMCVVMxIjAgBgNVBAoTGUdvb2dsZSBUcnVzdCBTZXJ2aWNlcyBM\n" + 
    "TEMxEzARBgNVBAMTCkdUUyBDQSAxQzMwHhcNMjExMTI5MDIyMjMzWhcNMjIwMjIx\n" + 
    "MDIyMjMyWjAXMRUwEwYDVQQDDAwqLmdvb2dsZS5jb20wWTATBgcqhkjOPQIBBggq\n" + 
    "hkjOPQMBBwNCAAShwtJ0zDJohmgYDI9a4Sxu+2c8JyYLtfnS/wdyRoIXUchfFuyr\n" + 
    "WO+bwp1BW6Fkauoqu0LeDXO8oysHN8gba4Vdo4ILkzCCC48wDgYDVR0PAQH/BAQD\n" + 
    "AgeAMBMGA1UdJQQMMAoGCCsGAQUFBwMBMAwGA1UdEwEB/wQCMAAwHQYDVR0OBBYE\n" + 
    "FAQOcJ1gEZcBGw5+crGZ5Sj3KeByMB8GA1UdIwQYMBaAFIp0f6+Fze6VzT2c0OJG\n" + 
    "FPNxNR0nMGoGCCsGAQUFBwEBBF4wXDAnBggrBgEFBQcwAYYbaHR0cDovL29jc3Au\n" + 
    "cGtpLmdvb2cvZ3RzMWMzMDEGCCsGAQUFBzAChiVodHRwOi8vcGtpLmdvb2cvcmVw\n" + 
    "by9jZXJ0cy9ndHMxYzMuZGVyMIIJQgYDVR0RBIIJOTCCCTWCDCouZ29vZ2xlLmNv\n" + 
    "bYIWKi5hcHBlbmdpbmUuZ29vZ2xlLmNvbYIJKi5iZG4uZGV2ghIqLmNsb3VkLmdv\n" + 
    "b2dsZS5jb22CGCouY3Jvd2Rzb3VyY2UuZ29vZ2xlLmNvbYIYKi5kYXRhY29tcHV0\n" + 
    "ZS5nb29nbGUuY29tggsqLmdvb2dsZS5jYYILKi5nb29nbGUuY2yCDiouZ29vZ2xl\n" + 
    "LmNvLmlugg4qLmdvb2dsZS5jby5qcIIOKi5nb29nbGUuY28udWuCDyouZ29vZ2xl\n" + 
    "LmNvbS5hcoIPKi5nb29nbGUuY29tLmF1gg8qLmdvb2dsZS5jb20uYnKCDyouZ29v\n" + 
    "Z2xlLmNvbS5jb4IPKi5nb29nbGUuY29tLm14gg8qLmdvb2dsZS5jb20udHKCDyou\n" + 
    "Z29vZ2xlLmNvbS52boILKi5nb29nbGUuZGWCCyouZ29vZ2xlLmVzggsqLmdvb2ds\n" + 
    "ZS5mcoILKi5nb29nbGUuaHWCCyouZ29vZ2xlLml0ggsqLmdvb2dsZS5ubIILKi5n\n" + 
    "b29nbGUucGyCCyouZ29vZ2xlLnB0ghIqLmdvb2dsZWFkYXBpcy5jb22CDyouZ29v\n" + 
    "Z2xlYXBpcy5jboIRKi5nb29nbGV2aWRlby5jb22CDCouZ3N0YXRpYy5jboIQKi5n\n" + 
    "c3RhdGljLWNuLmNvbYIPZ29vZ2xlY25hcHBzLmNughEqLmdvb2dsZWNuYXBwcy5j\n" + 
    "boIRZ29vZ2xlYXBwcy1jbi5jb22CEyouZ29vZ2xlYXBwcy1jbi5jb22CDGdrZWNu\n" + 
    "YXBwcy5jboIOKi5na2VjbmFwcHMuY26CEmdvb2dsZWRvd25sb2Fkcy5jboIUKi5n\n" + 
    "b29nbGVkb3dubG9hZHMuY26CEHJlY2FwdGNoYS5uZXQuY26CEioucmVjYXB0Y2hh\n" + 
    "Lm5ldC5jboILd2lkZXZpbmUuY26CDSoud2lkZXZpbmUuY26CEWFtcHByb2plY3Qu\n" + 
    "b3JnLmNughMqLmFtcHByb2plY3Qub3JnLmNughFhbXBwcm9qZWN0Lm5ldC5jboIT\n" + 
    "Ki5hbXBwcm9qZWN0Lm5ldC5jboIXZ29vZ2xlLWFuYWx5dGljcy1jbi5jb22CGSou\n" + 
    "Z29vZ2xlLWFuYWx5dGljcy1jbi5jb22CF2dvb2dsZWFkc2VydmljZXMtY24uY29t\n" + 
    "ghkqLmdvb2dsZWFkc2VydmljZXMtY24uY29tghFnb29nbGV2YWRzLWNuLmNvbYIT\n" + 
    "Ki5nb29nbGV2YWRzLWNuLmNvbYIRZ29vZ2xlYXBpcy1jbi5jb22CEyouZ29vZ2xl\n" + 
    "YXBpcy1jbi5jb22CFWdvb2dsZW9wdGltaXplLWNuLmNvbYIXKi5nb29nbGVvcHRp\n" + 
    "bWl6ZS1jbi5jb22CEmRvdWJsZWNsaWNrLWNuLm5ldIIUKi5kb3VibGVjbGljay1j\n" + 
    "bi5uZXSCGCouZmxzLmRvdWJsZWNsaWNrLWNuLm5ldIIWKi5nLmRvdWJsZWNsaWNr\n" + 
    "LWNuLm5ldIIOZG91YmxlY2xpY2suY26CECouZG91YmxlY2xpY2suY26CFCouZmxz\n" + 
    "LmRvdWJsZWNsaWNrLmNughIqLmcuZG91YmxlY2xpY2suY26CEWRhcnRzZWFyY2gt\n" + 
    "Y24ubmV0ghMqLmRhcnRzZWFyY2gtY24ubmV0gh1nb29nbGV0cmF2ZWxhZHNlcnZp\n" + 
    "Y2VzLWNuLmNvbYIfKi5nb29nbGV0cmF2ZWxhZHNlcnZpY2VzLWNuLmNvbYIYZ29v\n" + 
    "Z2xldGFnc2VydmljZXMtY24uY29tghoqLmdvb2dsZXRhZ3NlcnZpY2VzLWNuLmNv\n" + 
    "bYIXZ29vZ2xldGFnbWFuYWdlci1jbi5jb22CGSouZ29vZ2xldGFnbWFuYWdlci1j\n" + 
    "bi5jb22CGGdvb2dsZXN5bmRpY2F0aW9uLWNuLmNvbYIaKi5nb29nbGVzeW5kaWNh\n" + 
    "dGlvbi1jbi5jb22CJCouc2FmZWZyYW1lLmdvb2dsZXN5bmRpY2F0aW9uLWNuLmNv\n" + 
    "bYIWYXBwLW1lYXN1cmVtZW50LWNuLmNvbYIYKi5hcHAtbWVhc3VyZW1lbnQtY24u\n" + 
    "Y29tggtndnQxLWNuLmNvbYINKi5ndnQxLWNuLmNvbYILZ3Z0Mi1jbi5jb22CDSou\n" + 
    "Z3Z0Mi1jbi5jb22CCzJtZG4tY24ubmV0gg0qLjJtZG4tY24ubmV0ghRnb29nbGVm\n" + 
    "bGlnaHRzLWNuLm5ldIIWKi5nb29nbGVmbGlnaHRzLWNuLm5ldIIMYWRtb2ItY24u\n" + 
    "Y29tgg4qLmFkbW9iLWNuLmNvbYINKi5nc3RhdGljLmNvbYIUKi5tZXRyaWMuZ3N0\n" + 
    "YXRpYy5jb22CCiouZ3Z0MS5jb22CESouZ2NwY2RuLmd2dDEuY29tggoqLmd2dDIu\n" + 
    "Y29tgg4qLmdjcC5ndnQyLmNvbYIQKi51cmwuZ29vZ2xlLmNvbYIWKi55b3V0dWJl\n" + 
    "LW5vY29va2llLmNvbYILKi55dGltZy5jb22CC2FuZHJvaWQuY29tgg0qLmFuZHJv\n" + 
    "aWQuY29tghMqLmZsYXNoLmFuZHJvaWQuY29tggRnLmNuggYqLmcuY26CBGcuY2+C\n" + 
    "BiouZy5jb4IGZ29vLmdsggp3d3cuZ29vLmdsghRnb29nbGUtYW5hbHl0aWNzLmNv\n" + 
    "bYIWKi5nb29nbGUtYW5hbHl0aWNzLmNvbYIKZ29vZ2xlLmNvbYISZ29vZ2xlY29t\n" + 
    "bWVyY2UuY29tghQqLmdvb2dsZWNvbW1lcmNlLmNvbYIIZ2dwaHQuY26CCiouZ2dw\n" + 
    "aHQuY26CCnVyY2hpbi5jb22CDCoudXJjaGluLmNvbYIIeW91dHUuYmWCC3lvdXR1\n" + 
    "YmUuY29tgg0qLnlvdXR1YmUuY29tghR5b3V0dWJlZWR1Y2F0aW9uLmNvbYIWKi55\n" + 
    "b3V0dWJlZWR1Y2F0aW9uLmNvbYIPeW91dHViZWtpZHMuY29tghEqLnlvdXR1YmVr\n" + 
    "aWRzLmNvbYIFeXQuYmWCByoueXQuYmWCGmFuZHJvaWQuY2xpZW50cy5nb29nbGUu\n" + 
    "Y29tghtkZXZlbG9wZXIuYW5kcm9pZC5nb29nbGUuY26CHGRldmVsb3BlcnMuYW5k\n" + 
    "cm9pZC5nb29nbGUuY26CGHNvdXJjZS5hbmRyb2lkLmdvb2dsZS5jbjAhBgNVHSAE\n" + 
    "GjAYMAgGBmeBDAECATAMBgorBgEEAdZ5AgUDMDwGA1UdHwQ1MDMwMaAvoC2GK2h0\n" + 
    "dHA6Ly9jcmxzLnBraS5nb29nL2d0czFjMy9RcUZ4Ymk5TTQ4Yy5jcmwwggEFBgor\n" + 
    "BgEEAdZ5AgQCBIH2BIHzAPEAdwApeb7wnjk5IfBWc59jpXflvld9nGAK+PlNXSZc\n" + 
    "JV3HhAAAAX1pt0b0AAAEAwBIMEYCIQC1x9lAp2IZtthi0mxfjtSDXCR714tElKsl\n" + 
    "M61c4kJv/gIhAJPjgGFY5XBnQSaV/jHFfoRcbksMU+lruflT+sfvLLqcAHYAQcjK\n" + 
    "sd8iRkoQxqE6CUKHXk4xixsD6+tLx2jwkGKWBvYAAAF9abdHmAAABAMARzBFAiEA\n" + 
    "hzUCqPYG77z0wZUE3y2DmZhTaStBB9BMVBfYSQmYTogCIHbA8hBzj6kjpaw57Zid\n" + 
    "tP5Dz5Zli2xOJQB+W4s15tI8MA0GCSqGSIb3DQEBCwUAA4IBAQB2aorYRKwUQJI2\n" + 
    "80q1y1Q2Z8c6pem1MWxRX/Ptapmsp1ucrsmu+VaC1YMCSlW1exVieyMhOIvcKDBy\n" + 
    "/8L0FUEAmsL8fCTroRQ4DnZVLelFk9vmVsiSQtfYHOf6rwEbOrv+94kk964iQCLQ\n" + 
    "FYN5klRqI0hWa3wYe6tnXm/2PvPbAwqsnAq3q+Iek+3pGm6YTshJyA7P9L176psd\n" + 
    "dm6slAYpHOryFcrvXzu1lHSylCAFNT/OYcH1GLTf0qJXuN7YnX9swoYu2oCDkIyA\n" + 
    "Hss2DDp7f8qf0VgDNNxZB8drZ9ID85YA3qgeIbHHAB8UIj8qkKXkmybfMxVL0lz3\n" + 
    "iY73asSm\n" + 
    "-----END CERTIFICATE-----\n";
    
    
    // Google intermediate certificate
    public static final String CERTIFICATE2 = "" + //
            "-----BEGIN CERTIFICATE-----\n" + 
            "MIIFljCCA36gAwIBAgINAgO8U1lrNMcY9QFQZjANBgkqhkiG9w0BAQsFADBHMQsw\n" + 
            "CQYDVQQGEwJVUzEiMCAGA1UEChMZR29vZ2xlIFRydXN0IFNlcnZpY2VzIExMQzEU\n" + 
            "MBIGA1UEAxMLR1RTIFJvb3QgUjEwHhcNMjAwODEzMDAwMDQyWhcNMjcwOTMwMDAw\n" + 
            "MDQyWjBGMQswCQYDVQQGEwJVUzEiMCAGA1UEChMZR29vZ2xlIFRydXN0IFNlcnZp\n" + 
            "Y2VzIExMQzETMBEGA1UEAxMKR1RTIENBIDFDMzCCASIwDQYJKoZIhvcNAQEBBQAD\n" + 
            "ggEPADCCAQoCggEBAPWI3+dijB43+DdCkH9sh9D7ZYIl/ejLa6T/belaI+KZ9hzp\n" + 
            "kgOZE3wJCor6QtZeViSqejOEH9Hpabu5dOxXTGZok3c3VVP+ORBNtzS7XyV3NzsX\n" + 
            "lOo85Z3VvMO0Q+sup0fvsEQRY9i0QYXdQTBIkxu/t/bgRQIh4JZCF8/ZK2VWNAcm\n" + 
            "BA2o/X3KLu/qSHw3TT8An4Pf73WELnlXXPxXbhqW//yMmqaZviXZf5YsBvcRKgKA\n" + 
            "gOtjGDxQSYflispfGStZloEAoPtR28p3CwvJlk/vcEnHXG0g/Zm0tOLKLnf9LdwL\n" + 
            "tmsTDIwZKxeWmLnwi/agJ7u2441Rj72ux5uxiZ0CAwEAAaOCAYAwggF8MA4GA1Ud\n" + 
            "DwEB/wQEAwIBhjAdBgNVHSUEFjAUBggrBgEFBQcDAQYIKwYBBQUHAwIwEgYDVR0T\n" + 
            "AQH/BAgwBgEB/wIBADAdBgNVHQ4EFgQUinR/r4XN7pXNPZzQ4kYU83E1HScwHwYD\n" + 
            "VR0jBBgwFoAU5K8rJnEaK0gnhS9SZizv8IkTcT4waAYIKwYBBQUHAQEEXDBaMCYG\n" + 
            "CCsGAQUFBzABhhpodHRwOi8vb2NzcC5wa2kuZ29vZy9ndHNyMTAwBggrBgEFBQcw\n" + 
            "AoYkaHR0cDovL3BraS5nb29nL3JlcG8vY2VydHMvZ3RzcjEuZGVyMDQGA1UdHwQt\n" + 
            "MCswKaAnoCWGI2h0dHA6Ly9jcmwucGtpLmdvb2cvZ3RzcjEvZ3RzcjEuY3JsMFcG\n" + 
            "A1UdIARQME4wOAYKKwYBBAHWeQIFAzAqMCgGCCsGAQUFBwIBFhxodHRwczovL3Br\n" + 
            "aS5nb29nL3JlcG9zaXRvcnkvMAgGBmeBDAECATAIBgZngQwBAgIwDQYJKoZIhvcN\n" + 
            "AQELBQADggIBAIl9rCBcDDy+mqhXlRu0rvqrpXJxtDaV/d9AEQNMwkYUuxQkq/BQ\n" + 
            "cSLbrcRuf8/xam/IgxvYzolfh2yHuKkMo5uhYpSTld9brmYZCwKWnvy15xBpPnrL\n" + 
            "RklfRuFBsdeYTWU0AIAaP0+fbH9JAIFTQaSSIYKCGvGjRFsqUBITTcFTNvNCCK9U\n" + 
            "+o53UxtkOCcXCb1YyRt8OS1b887U7ZfbFAO/CVMkH8IMBHmYJvJh8VNS/UKMG2Yr\n" + 
            "PxWhu//2m+OBmgEGcYk1KCTd4b3rGS3hSMs9WYNRtHTGnXzGsYZbr8w0xNPM1IER\n" + 
            "lQCh9BIiAfq0g3GvjLeMcySsN1PCAJA/Ef5c7TaUEDu9Ka7ixzpiO2xj2YC/WXGs\n" + 
            "Yye5TBeg2vZzFb8q3o/zpWwygTMD0IZRcZk0upONXbVRWPeyk+gB9lm+cZv9TSjO\n" + 
            "z23HFtz30dZGm6fKa+l3D/2gthsjgx0QGtkJAITgRNOidSOzNIb2ILCkXhAd4FJG\n" + 
            "AJ2xDx8hcFH1mt0G/FX0Kw4zd8NLQsLxdxP8c4CU6x+7Nz/OAipmsHMdMqUybDKw\n" + 
            "juDEI/9bfU1lcKwrmz3O2+BtjjKAvpafkmO8l7tdufThcV4q5O8DIrGKZTqPwJNl\n" + 
            "1IXNDw9bg1kWRxYtnCQ6yICmJhSFm/Y3m6xv+cXDBlHz4n/FsRC6UfTd\n" + 
            "-----END CERTIFICATE-----\n"; 
    
    // Google ROOT certificate
    public static final String ROOT_CERTIFICATE = "" + //
            "-----BEGIN CERTIFICATE-----\n" + 
            "MIIFYjCCBEqgAwIBAgIQd70NbNs2+RrqIQ/E8FjTDTANBgkqhkiG9w0BAQsFADBX\n" + 
            "MQswCQYDVQQGEwJCRTEZMBcGA1UEChMQR2xvYmFsU2lnbiBudi1zYTEQMA4GA1UE\n" + 
            "CxMHUm9vdCBDQTEbMBkGA1UEAxMSR2xvYmFsU2lnbiBSb290IENBMB4XDTIwMDYx\n" + 
            "OTAwMDA0MloXDTI4MDEyODAwMDA0MlowRzELMAkGA1UEBhMCVVMxIjAgBgNVBAoT\n" + 
            "GUdvb2dsZSBUcnVzdCBTZXJ2aWNlcyBMTEMxFDASBgNVBAMTC0dUUyBSb290IFIx\n" + 
            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAthECix7joXebO9y/lD63\n" + 
            "ladAPKH9gvl9MgaCcfb2jH/76Nu8ai6Xl6OMS/kr9rH5zoQdsfnFl97vufKj6bwS\n" + 
            "iV6nqlKr+CMny6SxnGPb15l+8Ape62im9MZaRw1NEDPjTrETo8gYbEvs/AmQ351k\n" + 
            "KSUjB6G00j0uYODP0gmHu81I8E3CwnqIiru6z1kZ1q+PsAewnjHxgsHA3y6mbWwZ\n" + 
            "DrXYfiYaRQM9sHmklCitD38m5agI/pboPGiUU+6DOogrFZYJsuB6jC511pzrp1Zk\n" + 
            "j5ZPaK49l8KEj8C8QMALXL32h7M1bKwYUH+E4EzNktMg6TO8UpmvMrUpsyUqtEj5\n" + 
            "cuHKZPfmghCN6J3Cioj6OGaK/GP5Afl4/Xtcd/p2h/rs37EOeZVXtL0m79YB0esW\n" + 
            "CruOC7XFxYpVq9Os6pFLKcwZpDIlTirxZUTQAs6qzkm06p98g7BAe+dDq6dso499\n" + 
            "iYH6TKX/1Y7DzkvgtdizjkXPdsDtQCv9Uw+wp9U7DbGKogPeMa3Md+pvez7W35Ei\n" + 
            "Eua++tgy/BBjFFFy3l3WFpO9KWgz7zpm7AeKJt8T11dleCfeXkkUAKIAf5qoIbap\n" + 
            "sZWwpbkNFhHax2xIPEDgfg1azVY80ZcFuctL7TlLnMQ/0lUTbiSw1nH69MG6zO0b\n" + 
            "9f6BQdgAmD06yK56mDcYBZUCAwEAAaOCATgwggE0MA4GA1UdDwEB/wQEAwIBhjAP\n" + 
            "BgNVHRMBAf8EBTADAQH/MB0GA1UdDgQWBBTkrysmcRorSCeFL1JmLO/wiRNxPjAf\n" + 
            "BgNVHSMEGDAWgBRge2YaRQ2XyolQL30EzTSo//z9SzBgBggrBgEFBQcBAQRUMFIw\n" + 
            "JQYIKwYBBQUHMAGGGWh0dHA6Ly9vY3NwLnBraS5nb29nL2dzcjEwKQYIKwYBBQUH\n" + 
            "MAKGHWh0dHA6Ly9wa2kuZ29vZy9nc3IxL2dzcjEuY3J0MDIGA1UdHwQrMCkwJ6Al\n" + 
            "oCOGIWh0dHA6Ly9jcmwucGtpLmdvb2cvZ3NyMS9nc3IxLmNybDA7BgNVHSAENDAy\n" + 
            "MAgGBmeBDAECATAIBgZngQwBAgIwDQYLKwYBBAHWeQIFAwIwDQYLKwYBBAHWeQIF\n" + 
            "AwMwDQYJKoZIhvcNAQELBQADggEBADSkHrEoo9C0dhemMXoh6dFSPsjbdBZBiLg9\n" + 
            "NR3t5P+T4Vxfq7vqfM/b5A3Ri1fyJm9bvhdGaJQ3b2t6yMAYN/olUazsaL+yyEn9\n" + 
            "WprKASOshIArAoyZl+tJaox118fessmXn1hIVw41oeQa1v1vg4Fv74zPl6/AhSrw\n" + 
            "9U5pCZEt4Wi4wStz6dTZ/CLANx8LZh1J7QJVj2fhMtfTJr9w4z30Z209fOU0iOMy\n" + 
            "+qduBmpvvYuR7hZL6Dupszfnw0Skfths18dG9ZKb59UhvmaSGZRVbNQpsg3BZlvi\n" + 
            "d0lIKO2d1xozclOzgjXPYovJJIultzkMu34qQb9Sz/yilrbCgj8=\n" + 
            "-----END CERTIFICATE-----\n"; 

    // CA Certificate
    public static final String CA_CERTIFICATE = "" + //
            "-----BEGIN CERTIFICATE-----\n" + 
            "MIIDdTCCAl2gAwIBAgILBAAAAAABFUtaw5QwDQYJKoZIhvcNAQEFBQAwVzELMAkG\n" + 
            "A1UEBhMCQkUxGTAXBgNVBAoTEEdsb2JhbFNpZ24gbnYtc2ExEDAOBgNVBAsTB1Jv\n" + 
            "b3QgQ0ExGzAZBgNVBAMTEkdsb2JhbFNpZ24gUm9vdCBDQTAeFw05ODA5MDExMjAw\n" + 
            "MDBaFw0yODAxMjgxMjAwMDBaMFcxCzAJBgNVBAYTAkJFMRkwFwYDVQQKExBHbG9i\n" + 
            "YWxTaWduIG52LXNhMRAwDgYDVQQLEwdSb290IENBMRswGQYDVQQDExJHbG9iYWxT\n" + 
            "aWduIFJvb3QgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDaDuaZ\n" + 
            "jc6j40+Kfvvxi4Mla+pIH/EqsLmVEQS98GPR4mdmzxzdzxtIK+6NiY6arymAZavp\n" + 
            "xy0Sy6scTHAHoT0KMM0VjU/43dSMUBUc71DuxC73/OlS8pF94G3VNTCOXkNz8kHp\n" + 
            "1Wrjsok6Vjk4bwY8iGlbKk3Fp1S4bInMm/k8yuX9ifUSPJJ4ltbcdG6TRGHRjcdG\n" + 
            "snUOhugZitVtbNV4FpWi6cgKOOvyJBNPc1STE4U6G7weNLWLBYy5d4ux2x8gkasJ\n" + 
            "U26Qzns3dLlwR5EiUWMWea6xrkEmCMgZK9FGqkjWZCrXgzT/LCrBbBlDSgeF59N8\n" + 
            "9iFo7+ryUp9/k5DPAgMBAAGjQjBAMA4GA1UdDwEB/wQEAwIBBjAPBgNVHRMBAf8E\n" + 
            "BTADAQH/MB0GA1UdDgQWBBRge2YaRQ2XyolQL30EzTSo//z9SzANBgkqhkiG9w0B\n" + 
            "AQUFAAOCAQEA1nPnfE920I2/7LqivjTFKDK1fPxsnCwrvQmeU79rXqoRSLblCKOz\n" + 
            "yj1hTdNGCbM+w6DjY1Ub8rrvrTnhQ7k4o+YviiY776BQVvnGCv04zcQLcFGUl5gE\n" + 
            "38NflNUVyRRBnMRddWQVDf9VMOyGj/8N7yy5Y0b2qvzfvGn9LhJIZJrglfCm7ymP\n" + 
            "AbEVtQwdpf5pLGkkeB6zpxxxYu7KyJesF12KwvhHhm4qxFYxldBniYUr+WymXUad\n" + 
            "DKqC5JlR3XC321Y9YeRq4VzW9v493kHMB65jUr9TU/Qr6cf9tveCX4XSQRjbgbME\n" + 
            "HMUfpIBvFSDJ3gyICh3WZlXi/EjJKSZp4A==\n" + 
            "-----END CERTIFICATE-----\n"; 
    
    private static final GGLogger LOG = GGLogger.getLogger(GGCertificateVerifierTest.class);
    
    @Test
    public void testCertificate() {
        
        GGCertificateVerifier verifier = new GGCertificateVerifierImpl();
        
        final Set<X509Certificate> intermediates = createSet(CERTIFICATE1, CERTIFICATE2, ROOT_CERTIFICATE);
        
        for(final X509Certificate cert : intermediates) {
            LOG.info("cert: %s", cert);
        }
        
        final Set<X509Certificate> roots = createSet(CA_CERTIFICATE);

        for(final X509Certificate cert : roots) {
            LOG.info("cert: %s", cert);
        }

        final GGCertificateVerifyResult result = verifier.verify(PKCS10Reader.readCertificate(CERTIFICATE1), intermediates, roots);
        
        LOG.info("result: %s", result);
        
        assertThat(result.isValid(), equalTo(true));
    
    }
    
    
    
    private Set<X509Certificate> createSet(final String ... pems){
        
        final Set<X509Certificate> set = new HashSet<>();
        
        
        for(final String pem : pems){
            
            set.add( PKCS10Reader.readCertificate(pem));
            
        }
        
        
        return set;
    }
}
