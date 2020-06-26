//
//  main.c
//  Test
//
//  Created by ParkMinwoo on 2016. 4. 20..
//  Copyright © 2016년 ParkMinwoo. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#define INSERT  1
#define DELETE  2
#define COUNT   3
#define HEIGHT  4
#define PREORDER 5
#define SORT    6
#define TERMINAL 7
#define NUMBER  8
#define QUIT    9
#define FIND_NUMBER_COUNT -2147483648

struct bst{
    int key;
    struct bst *left;   // Left child
    struct bst *right;  // Right child
};

void Insert(struct bst **);
void Delete(struct bst **);
int Count(struct bst *);
int Height(struct bst *);
void Preorder(struct bst *);
void Sort(struct bst *);
int Terminal(struct bst *);
int Number(struct bst *,int);
void print_menu();
void Quit();

int main(){
    int choice=1,input_data;
    int error=1;
    struct bst *root=NULL;
    while(1){
        do{
            print_menu();
            if(error!=1||choice==0) // Input Error
                printf("Input Error!\nChoice number must be number between 1~9!\nRe");
            
            printf("input Choice Number : ");
            
            fpurge(stdin); // Empty stdin buffer in Linux  OS
            fflush(stdin); // Empty stdin buffer in Window OS
            
            error = scanf("%d",&choice);
        }
        while(error!=1);
        switch(choice){
            case INSERT:
                printf("------------Insert------------\n");
                Insert(&root);
                printf("Insert success\n");
                break;
            case DELETE:
                printf("------------Delete------------\n");
                Delete(&root);
                break;
            case COUNT:
                printf("------------Count-------------\n");
                printf("Number of Nodes : %d\n",Count(root));
                break;
            case HEIGHT:
                printf("------------Height------------\n");
                printf("Height : %d\n",Height(root));
                break;
            case PREORDER:
                printf("------------Preorder----------\n");
                Preorder(root);
                printf("\n");
                break;
            case SORT:
                printf("------------Sort--------------\n");
                Sort(root);
                printf("\n");
                break;
            case TERMINAL:
                printf("------------Terminal-----------\n");
                printf("Terminal:%d\n",Terminal(root));
                break;
            case NUMBER:
                printf("------------Number-------------\n");
                while(1){
                    printf("Input Node key what you want to find : ");
                    scanf("%d",&input_data);
                    if(input_data==0)
                        printf("Error : Node Key must be integer!\n");
                    else
                        break;
                }
                if((input_data=Number(root,input_data))>0)
                    printf("Any node don't have input key in tree\n");
                else
                    printf("input key is %ld order\n",input_data-FIND_NUMBER_COUNT+1);
                break;
            case QUIT:
                Quit();
                break;
            default:
                break;
        }
    }
    return 0;
}
// Print Menu option
void print_menu(){
    printf("----------Menu-----------\n");
    printf("1) Insert New Node\n2) Delete Node by key\n3) Count Node\n4) Measure Tree Height\n5) Preorder Traversal\n6) Sort Tree\n7) Count Terminal\n8) Number\n9) Quit\n");
    printf("-------------------------\n");
}
//Insert node Method
void Insert(struct bst **root){
    int error=1; // insert data
    int data;
    do{
        if(error!=1)
            printf("Input Error\nNode Key must be integer!\nRe");
        printf("input node key what you want insert : ");
        
        fpurge(stdin); // Empty stdin buffer in Linux  OS
        fflush(stdin); // Empty stdin buffer in Window OS
        
        error = scanf("%d",&data);
    }
    while(error!=1);
    struct bst *new_bst = (struct bst *)malloc(sizeof(struct bst));
    struct bst *temp;
    
    temp = *root;
    new_bst->left = NULL;
    new_bst->right = NULL;
    new_bst->key = data;
    if(*root==NULL){ // when tree is empty
        *root = new_bst;
        return;
    }
    else
        while(1){
            if(data<temp->key){ // left node
                if(temp->left == NULL){
                    temp->left = new_bst;
                    return;
                }
                else
                    temp = temp->left;
            }
            else{ // right node
                if(temp->right == NULL){
                    temp->right = new_bst;
                    return;
                }
                else
                    temp = temp->right;
            }
        }
}
void Delete(struct bst **root){
    struct bst *p,*child,*succ,*succ_p,*t;
    int id;
    int error=1;
    if(*root==NULL){ // When tree is empty
        printf("Tree is empty\n");
        return;
    }
    do{
        if(error!=1)
            printf("Error : Node Key must be integer!\n");
        printf("Input node key what you want to delete :");
        fpurge(stdin); // Empty stdin buffer in Linux  OS
        fflush(stdin); // Empty stdin buffer in Window OS
        error = scanf("%d",&id);
    }
    while(error!=1);
    p = *root;
    t = *root;
    while(t!=NULL && t->key!=id){ // search node
        
        p=t;
        t = ( id < p->key) ? p->left : p->right;
    }
    
    if(t==NULL){ // search fail
        
        printf("Any node don't have key in tree\n");
        return;
    }
    
    if((t->left==NULL)&&(t->right==NULL)){ // When  Node is Terminal Node
        
        if(p==t) // When Delete Node is root
            *root=NULL;
        else{
            if( p!=NULL){
                if(p->left == t)
                    p->left=NULL;
                else
                    p->right = NULL;
            }
            else
                *root = NULL;
        }
        
    }
    else if((t->left==NULL)||(t->right==NULL)){ // When Node have one child
        child = (t->left !=NULL)?t->left :t->right;
        if( p!= NULL){
            if(p != NULL){
                if(p->left == t)
                    p->left = child;
                else
                    p->right = child;
                
            }
            else
                *root = child;
        }
        
    }
    else{ // when Node have two child
        succ_p =t;
        succ = t->right;
        
        while(succ->left !=NULL){
            succ_p = succ;
            succ = succ->left;
        }
        
        if(succ_p->left==succ)
            succ_p->left =succ->right;
        else
            succ_p->right = succ->right;
        
        t->key = succ->key;
        
        t= succ;
        
        
    }
    free(t);
    printf("Delete success\n");
}

int Count(struct bst *root){
    int sum=1;
    if(root==NULL)
        return 0;
    if(root->left!=NULL)
        sum+=Count(root->left);
    if(root->right!=NULL)
        sum+=Count(root->right);
    return sum;
}
int Height(struct bst *root){ // Tree Height
    
    struct bst *t;
    int height=1;
    int l_height=0;
    int r_height=0;
    t = root;
    
    if(t->left!=NULL)
        l_height = Height(t->left);
    if(t->right!=NULL)
        r_height = Height(t->right);
    
    if(l_height<=r_height)
        height+=r_height;
    else
        height+=l_height;
    
    return height;
}
void Sort(struct bst *node){
    if(node->left!=NULL)
        Sort(node->left);
    printf("%d ",node->key);
    if(node->right!=NULL)
        Sort(node->right);
}
void Preorder(struct bst *node){
    printf("%d ",node->key);
    if(node->left!=NULL)
        Preorder(node->left);
    if(node->right!=NULL)
        Preorder(node->right);
}
int Terminal(struct bst *node){ // number of Terminal nodes
    int sum=0;
    
    if(node->left!=NULL)
        sum+=Terminal(node->left);
    if(node->right!=NULL)
        sum+=Terminal(node->right);
    
    if(node->left==NULL&&node->right==NULL)
        sum++;
    
    return sum;
}
int Number(struct bst *node,int id){ // find count how
    int num=0;
    if(node->left!=NULL)
        num+=Number(node->left,id);
    if(node->key==id)
        return num+FIND_NUMBER_COUNT;
    else if(node->key<id){
        if(node->right!=NULL)
            num+=Number(node->right, id);
        return ++num;
    }
    else
        return num;
    return num;
}
void Quit(){
    printf("Exit.....\n");
    exit(1);
}
