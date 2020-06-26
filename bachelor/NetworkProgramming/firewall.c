#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<dirent.h>
#include<sys/types.h>
#include<fcntl.h>
#include<unistd.h>
#include<sys/stat.h>
#include<time.h>
#include<signal.h>
#include<pthread.h>
#include<errno.h>
#include<pwd.h>
#include<grp.h>
#include<math.h>
#include<sys/time.h>
#include<sys/wait.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<netdb.h>
#include<arpa/inet.h>
#include<netinet/ip.h>
#include<netinet/tcp.h>
#include<netinet/ip_icmp.h>
#include<netinet/in_systm.h>
#include<sys/resource.h>
#include<sys/ioctl.h>
#include<net/if.h>
#include<pthread.h>
   

void sig_int(int);
void print_tcp(struct tcphdr *tcph);
void print_ip(struct iphdr *iph);
void entertime_tcp(struct tcphdr *tcph);
void entertime_ip(struct iphdr *iph);
void block_tcp(struct tcphdr *tcph);
void block_ip(struct iphdr *iph);
void add_fire_addr(int*, struct in_addr***);
void add_fire_port(int*, unsigned int***);
void *do_keyborad(void *);
int checkcodebit(struct iphdr *,struct tcphdr *);
unsigned int my_pow(int x);
pthread_t thread;
pthread_mutex_t mutex;
  
char opencmd[] = "@";
char closecmd[] = "#";
char help[] = "?";
char daddr[] = "daddr";
char dport[] = "dport";
char removeaddr[] = "removeaddr";
char removeport[] = "removeport";
char removelist[] = "removelist";
char Exit[] = "exit";
  
  
struct tm *t;
time_t timer;
int entertime= 0; //명령어 입력할 동안 출력이 보이지 않게, 로그엔 저장됨.
FILE *fwlog;
FILE *blocklog;
FILE *iptables; //이동

 
struct in_addr **fire_addr;
unsigned int **fire_port;
unsigned int fire_port_size=0,fire_addr_size=0;
 
  
int main(int argv,char* argc[]){
   
    int i,j,n=0;
    int bitbool = 0;
    char temp[BUFSIZ];
    char tmp;
    struct sigaction act;
    int state;
    fire_addr = (struct in_addr**)malloc(sizeof(struct in_addr*));
    fire_port = (unsigned int**)malloc(sizeof(unsigned int*));
    iptables = fopen("iptables","r");

    act.sa_handler = sig_int;
    sigemptyset(&act.sa_mask);
    act.sa_flags = 0;

    state=sigaction(SIGINT,&act,0);


    while(!feof(iptables)){ // iptables 방화벽 대상 불러오기
        fgets(temp,sizeof(temp),iptables);
        if(feof(iptables)){
            break;
        }
        strtok(temp," ");
        if(strcmp(temp,"-dport")==0){
            fire_port =(unsigned int**)realloc(fire_port,sizeof(unsigned int*)*(fire_port_size+1));
            fire_port[fire_port_size] = (unsigned int*)malloc(sizeof(unsigned int)*2);
            fire_port[fire_port_size][0] = atoi(strtok(NULL,":"));
            fire_port[fire_port_size++][1] = atoi(strtok(NULL," "));
        }
        else{
            fire_addr =(struct in_addr**)realloc(fire_addr,sizeof(struct in_addr*)*(fire_addr_size+1));
            fire_addr[fire_addr_size] = (struct in_addr*)malloc(sizeof(struct in_addr)*2);       
            fire_addr[fire_addr_size][0].s_addr=inet_addr(strtok(NULL,"/"));
            fire_addr[fire_addr_size++][1].s_addr=htonl(htonl(fire_addr[fire_addr_size-1][0].s_addr)+(my_pow(32-atoi(strtok(NULL," "))))-1);
        }
    }
    fclose(iptables);
    int sd , len;
   
    int rx_packet_size = sizeof(struct iphdr) + sizeof(struct tcphdr);
    char *rx_packet = malloc(rx_packet_size);
       
    struct tcphdr *rx_tcph;
    struct iphdr *rx_iph;
       
    struct in_addr s_addr, d_addr;
    struct sockaddr_in local, remote;
       
    struct servent *serv;
       
    if((sd = socket(PF_INET, SOCK_RAW,IPPROTO_TCP))<0){
        printf("socket open error\n");
        exit(-1);
    }
    fwlog = fopen("fwlog","a+");
    blocklog = fopen("blocklog","a+");
    pthread_create(&thread, NULL, do_keyborad,NULL);
  
    while(1){
        bzero(rx_packet, rx_packet_size);
           
        len = sizeof(local);
        if(recvfrom(sd,rx_packet,rx_packet_size,0x0,(struct sockaddr *)&local, &len)<0) {
            printf("recfrom error\n");
            exit(-2);
        }
        rx_iph = (struct iphdr *)(rx_packet);
        rx_tcph = (struct tcphdr *)(rx_packet + rx_iph->ihl *4);
  
          
           
        for(j = 0 ; j < fire_addr_size;j++){
            if(htonl(rx_iph->saddr)>=htonl(fire_addr[j][0].s_addr)&&htonl(rx_iph->saddr)<=htonl(fire_addr[j][1].s_addr)){
                if(entertime == 0)                
                    printf("Addr error <block>\n");
                break;
            }  
        }
        for(i = 0 ; i < fire_port_size && j == fire_addr_size ;i++){
            if(ntohs(rx_tcph->dest)>=fire_port[i][0]&&ntohs(rx_tcph->dest)<=fire_port[i][1]||(rx_tcph->dest==rx_tcph->source&&ntohs(rx_tcph->source)<=1024)){
                if(entertime == 0)                  
                    printf("Port error <block>\n");
                break;
            }  
        }
        if(i == fire_port_size && j == fire_addr_size)
            bitbool = checkcodebit(rx_iph,rx_tcph);
          
            if(i==fire_port_size&&j==fire_addr_size&&bitbool==0){
                if(entertime == 0){     
                    print_ip(rx_iph);
                    print_tcp(rx_tcph);
                }else{
                    entertime_ip(rx_iph);
                    entertime_tcp(rx_tcph);         
                }
            }else{
                fwlog = fopen("fwlog","a+");
                blocklog = fopen("blocklog","a+");



                if(i!=fire_port_size){
                    fprintf(fwlog,"Port block ");
                    fprintf(blocklog,"Port block ");		
                }else if(j!=fire_addr_size){
                    fprintf(fwlog,"Addr block ");
                    fprintf(blocklog,"Addr block ");
                }else if(bitbool==1){
                    fprintf(fwlog,"codebit block ");
                    fprintf(blocklog,"codebit block ");
		}
                block_ip(rx_iph);
                block_tcp(rx_tcph); 

                if(entertime == 0) { 
                    print_ip(rx_iph);
                    print_tcp(rx_tcph);
                }else{
                    entertime_ip(rx_iph);
                    entertime_tcp(rx_tcph);         
                }
            }
    }
    close(sd);
}
void print_ip(struct iphdr *iph){
    printf("[IP HEADER] VER : %1u HL : %2u Protocol : %3u " , iph->version, iph->ihl,iph->version);
    fprintf(fwlog,"[IP HEADER] VER : %1u HL : %2u Protocol : %3u " , iph->version, iph->ihl,iph->version);
    printf("SRC IP: %15s ", inet_ntoa(*(struct in_addr *)&iph->saddr));
    fprintf(fwlog,"SRC IP: %15s ", inet_ntoa(*(struct in_addr *)&iph->saddr));
    printf("DEST IP: %15s\n",inet_ntoa(*(struct in_addr *)&iph->daddr));
    fprintf(fwlog,"DEST IP: %15s ",inet_ntoa(*(struct in_addr *)&iph->daddr));
    fclose(fwlog);
    fwlog = fopen("fwlog","a+");
}
void print_tcp(struct tcphdr *tcph){
    char  bit[BUFSIZ];
    timer = time(NULL);
    t = localtime(&timer);
    memset(bit, 0, sizeof(bit));
    printf("[TCP HEADR] src port : %5u dest port : %5u ", ntohs(tcph->source),ntohs(tcph->dest));
    fprintf(fwlog,"[TCP HEADR] src port : %5u dest port : %5u ", ntohs(tcph->source),ntohs(tcph->dest));
    (tcph->urg == 1)?printf("U"):printf("-");
    (tcph->ack == 1)?printf("A"):printf("-");
    (tcph->psh == 1)?printf("P"):printf("-");
    (tcph->rst == 1)?printf("R"):printf("-");
    (tcph->syn == 1)?printf("S"):printf("-");
    (tcph->fin == 1)?printf("F"):printf("-");
    printf("\n\n");
  
    if(tcph->urg == 1) strcat(bit,"U");
    else strcat(bit,"-");
    if(tcph->ack == 1) strcat(bit,"A");
    else strcat(bit,"-");
    if(tcph->psh == 1) strcat(bit,"P");
    else strcat(bit,"-");
    if(tcph->urg == 1) strcat(bit,"U");
    else strcat(bit,"-");
    if(tcph->rst == 1) strcat(bit,"R");
    else strcat(bit,"-");
    if(tcph->syn == 1) strcat(bit,"S");
    else strcat(bit,"-");
    if(tcph->fin == 1) strcat(bit,"F");
    else strcat(bit,"-");
  
    fprintf(fwlog,"%s",bit);
    fprintf(fwlog," Time : %s",asctime(t));
    fclose(fwlog);
    fwlog = fopen("fwlog","a+");
}
int checkcodebit(struct iphdr *iph,struct tcphdr *tcph)
{
    char  bit[BUFSIZ];
    memset(bit, 0, sizeof(bit));
  
    if((tcph->syn==1&&tcph->fin==1) || 
(tcph->syn==1&&tcph->rst==1) ||
(tcph->fin==1&&tcph->rst==1) ||
(tcph->ack==0&&tcph->fin==1) ||
(tcph->ack==0&&tcph->psh==1) ||
(tcph->ack==0&&tcph->urg==1) ||
(tcph->urg==0&&tcph->ack==0&&tcph->psh==0&&tcph->rst==0&&tcph->syn==0&&tcph->fin==1) ||
(tcph->urg==0&&tcph->ack==0&&tcph->psh==0&&tcph->rst==0&&tcph->syn==0&&tcph->fin==0) ||
(tcph->urg==0&&tcph->ack==0&&tcph->psh==1&&tcph->rst==0&&tcph->syn==0&&tcph->fin==1))
    {   
        if(entertime==0)
            printf("codebit error <block>\n");
        return 1;
    }   
    else
        return 0;
}
void *do_keyborad(void *arg)
{
    char   chatData[BUFSIZ];
    char tok[BUFSIZ];
    int   i, n,cnt=1;
   
    while(1) {
        memset(chatData, 0, sizeof(chatData));
    memset(tok, 0, sizeof(tok));
        if((n = read(0, chatData, sizeof(chatData))) > 0) {
            if(strstr(chatData, opencmd) != NULL) {
                printf("opencmd\n\n");
                entertime = 1;
            }
            else if (strstr(chatData, closecmd) != NULL) {
                printf("closecmd\n\n");
                entertime = 0;
            }
            else if(strstr(chatData,help)!= NULL) {
		printf("  ---------------<<help>>--------------\n");
                printf("  **command\t  (explanation)**\n");
                printf("  daddr ip/subnet (permit -> block ip)\n");
                printf("  dport port:port (permit -> block port)\n");
                printf("  removelist (blocked list)\n");
                printf("  removeaddr num (block -> permit)\n");
                printf("  removeport num (block -> permit)\n");
		printf("  -------------------------------------\n\n");
            }  
            else if(strstr(chatData,daddr)!= NULL) {
                strtok(chatData," ");
                strcpy(tok,strtok(NULL,"\n"));
                iptables = fopen("iptables","a");
                fprintf(iptables,"-daddr %s -j DROP\n",tok);
                fclose(iptables);
                fire_addr =(struct in_addr**)realloc(fire_addr,sizeof(struct in_addr*)*(fire_addr_size+1));
                fire_addr[fire_addr_size] = (struct in_addr*)malloc(sizeof(struct in_addr)*2);       
                fire_addr[fire_addr_size][0].s_addr=inet_addr(strtok(tok,"/"));
                fire_addr[fire_addr_size++][1].s_addr=htonl(htonl(fire_addr[fire_addr_size-1][0].s_addr)+(my_pow(32-atoi(strtok(NULL," "))))-1);
                printf("complete\n\n");
            }  
            else if(strstr(chatData,dport)!= NULL) {
                strtok(chatData," ");
                strcpy(tok,strtok(NULL,"\n"));
                iptables = fopen("iptables","a");
                fprintf(iptables,"-dport %s -j DROP\n",tok);
                fclose(iptables);
                fire_port =(unsigned int**)realloc(fire_port,sizeof(unsigned int*)*(fire_port_size+1));
                fire_port[fire_port_size] = (unsigned int*)malloc(sizeof(unsigned int)*2);
                fire_port[fire_port_size][0] = atoi(strtok(tok,":"));
                fire_port[fire_port_size++][1] = atoi(strtok(NULL," "));
                printf("complete\n\n");
            }  
            else if(strstr(chatData,removeaddr)!= NULL) {
				strtok(chatData," ");
                i = atoi(strtok(NULL,"\n"))-1;
                for(;i<(fire_addr_size-1);i++)
                    fire_addr[i]=fire_addr[i+1];
                
				fire_addr_size--;
                iptables = fopen("iptables","w");               
                for(i=0;i<fire_addr_size;i++){
					if(fire_addr[i][1].s_addr-fire_addr[i][0].s_addr==0)
						fprintf(iptables,"-daddr %s/%d -j DROP\n",inet_ntoa(fire_addr[i][0]),32);
					else
                    	fprintf(iptables,"-daddr %s/%.0f -j DROP\n",inet_ntoa(fire_addr[i][0]),(32-log(htonl(fire_addr[i][1].s_addr-fire_addr[i][0].s_addr))/log(2)));
                }
                for(i=0;i<fire_port_size;i++){
                    fprintf(iptables,"-dport %d:%d -j DROP\n",fire_port[i][0],fire_port[i][1]);
                }
                fclose(iptables);
				printf("complete\n\n");
            }  
            else if(strstr(chatData,removeport)!= NULL) {
				strtok(chatData," ");
                i = atoi(strtok(NULL,"\n"))-1;
                for(;i<fire_port_size-1;i++){
                    fire_port[i]=fire_port[i+1];
                }
                fire_port_size--;
                iptables = fopen("iptables","w");               
                for(i=0;i<fire_addr_size;i++){
					if(fire_addr[i][1].s_addr-fire_addr[i][0].s_addr==0)
						fprintf(iptables,"-daddr %s/%d -j DROP\n",inet_ntoa(fire_addr[i][0]),32);
					else
                    	fprintf(iptables,"-daddr %s/%.0f -j DROP\n",inet_ntoa(fire_addr[i][0]),(32-log(htonl(fire_addr[i][1].s_addr-fire_addr[i][0].s_addr))/log(2)));
                }
                for(i=0;i<fire_port_size;i++){
                    fprintf(iptables,"-dport %d:%d -j DROP\n",fire_port[i][0],fire_port[i][1]);
                }
                fclose(iptables);
				printf("complete\n\n");
            }
            else if(strstr(chatData,removelist)!= NULL){
                printf("  * Port Block\n");
                for(i=1;i<fire_port_size+1;i++)
                    printf(" %d.Port: %u ~ %u -j DROP \n",i,fire_port[i-1][0],fire_port[i-1][1]);
                printf("  * Addr Block\n");             
                for(i=1;i<fire_addr_size+1;i++){
                    if(fire_addr[i-1][1].s_addr-fire_addr[i-1][0].s_addr==0)
                        printf(" %d.Addr: %s/%d -j DROP \n",i,inet_ntoa(fire_addr[i-1][0]),32);
                    else
                        printf(" %d.Addr: %s/%.0lf -j DROP \n",i,inet_ntoa(fire_addr[i-1][0]),(32-log(htonl(fire_addr[i-1][1].s_addr-fire_addr[i-1][0].s_addr))/log(2)));
                }
         
            }
            else {
                printf("This is not a valid input.\n");
            }
                  
        } 
    }
}
unsigned int my_pow(int x){
  
    unsigned int sum=1;
    int i=0;
    while(i<x){
        sum*=2;
        i++;
    }   
    return sum;
}
  
void entertime_ip(struct iphdr *iph){
    fprintf(fwlog,"[IP HEADER] VER : %1u HL : %2u Protocol : %3u " , iph->version, iph->ihl,iph->version);
    fprintf(fwlog,"SRC IP: %15s ", inet_ntoa(*(struct in_addr *)&iph->saddr));
    fprintf(fwlog,"DEST IP: %15s ",inet_ntoa(*(struct in_addr *)&iph->daddr));
    fclose(fwlog);
    fwlog = fopen("fwlog","a+");
}
void entertime_tcp(struct tcphdr *tcph){
    char  bit[BUFSIZ];
    timer = time(NULL);
    t = localtime(&timer);

    memset(bit, 0, sizeof(bit));
    fprintf(fwlog,"[TCP HEADR] src port : %5u dest port : %5u ", ntohs(tcph->source),ntohs(tcph->dest));
  
    if(tcph->urg == 1) strcat(bit,"U");
    else strcat(bit,"-");
    if(tcph->ack == 1) strcat(bit,"A");
    else strcat(bit,"-");
    if(tcph->psh == 1) strcat(bit,"P");
    else strcat(bit,"-");
    if(tcph->urg == 1) strcat(bit,"U");
    else strcat(bit,"-");
    if(tcph->rst == 1) strcat(bit,"R");
    else strcat(bit,"-");
    if(tcph->syn == 1) strcat(bit,"S");
    else strcat(bit,"-");
    if(tcph->fin == 1) strcat(bit,"F");
    else strcat(bit,"-");
  
    fprintf(fwlog,"%s",bit);
    fprintf(fwlog," Time : %s",asctime(t));
    fclose(fwlog);
    fwlog = fopen("fwlog","a+");
}

void block_ip(struct iphdr *iph){
    fprintf(blocklog,"[IP HEADER]VER:%1u HL:%1u Protocol:%1u " , iph->version, iph->ihl,iph->version);
    fprintf(blocklog,"SRC IP:%1s ", inet_ntoa(*(struct in_addr *)&iph->saddr));
    fprintf(blocklog,"DEST IP:%1s ",inet_ntoa(*(struct in_addr *)&iph->daddr));
    fclose(blocklog);
    blocklog = fopen("blocklog","a+");
}
void block_tcp(struct tcphdr *tcph){
    char  bit[BUFSIZ];
    timer = time(NULL);
    t = localtime(&timer);

    memset(bit, 0, sizeof(bit));
    fprintf(blocklog,"[TCP HEADR]src port:%1u dest port:%1u ", ntohs(tcph->source),ntohs(tcph->dest));
  
    if(tcph->urg == 1) strcat(bit,"U");
    else strcat(bit,"-");
    if(tcph->ack == 1) strcat(bit,"A");
    else strcat(bit,"-");
    if(tcph->psh == 1) strcat(bit,"P");
    else strcat(bit,"-");
    if(tcph->urg == 1) strcat(bit,"U");
    else strcat(bit,"-");
    if(tcph->rst == 1) strcat(bit,"R");
    else strcat(bit,"-");
    if(tcph->syn == 1) strcat(bit,"S");
    else strcat(bit,"-");
    if(tcph->fin == 1) strcat(bit,"F");
    else strcat(bit,"-");
  
    fprintf(blocklog,"%s",bit);
    fprintf(blocklog,"Time:%s",asctime(t));
    fclose(blocklog);
    blocklog = fopen("blocklog","a+");
}
void sig_int(int signo)
{
	printf(" FireWall shut down!!\n");
	fclose(fwlog);
	fclose(blocklog);
	exit(-1);
}

