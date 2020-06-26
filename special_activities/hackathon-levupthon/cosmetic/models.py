from django.db import models
from django.contrib.auth.models import User
from django.db.models import Avg
# Create your models here.

class Brand(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100)
    
    
    def __str__(self):
        return self.name
    
class Type(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100)
    
    def __str__(self):
        return self.name
        
    
class Cosmetic(models.Model):
    id = models.AutoField(primary_key=True)
    type = models.ForeignKey(Type, on_delete=models.CASCADE)
    brand = models.ForeignKey(Brand, on_delete=models.CASCADE)
    name = models.CharField(max_length=100)
    
    description = models.TextField()
    thumbnail = models.ImageField(upload_to='images/cosmetics')

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    
    @property
    def rate_avg(self):
        rate = Comment.objects.filter(cosmetic=self).aggregate(Avg('rate'))
        
        return rate['rate__avg']
        
    @property
    def summary(self):
        return self.description[:20]

class CosmeticImage(models.Model):
    cosmetic = models.ForeignKey(Cosmetic, related_name='images', on_delete=models.CASCADE)
    image = models.ImageField(upload_to='images/cosmetics')
 
class Comment(models.Model):
    
    cosmetic = models.ForeignKey(Cosmetic, on_delete=models.SET_NULL, null=True)
    content = models.TextField()
    user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    rate = models.PositiveIntegerField(default=3)
    created_at = models.DateTimeField(auto_now_add=True)
    

class Like(models.Model):
    comment = models.ForeignKey(Comment, on_delete=models.CASCADE)
    user = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    created_at = models.DateTimeField(auto_now_add=True)


    