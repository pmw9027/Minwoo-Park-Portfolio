from django.contrib import admin
from .models import Cosmetic, Brand, Type, Comment, CosmeticImage
from django.db.models.fields import Field
from django.db.models.fields.related import ManyToManyField
from datetime import datetime, timedelta
  
class CosmeticImageInline(admin.TabularInline):
    model = CosmeticImage
    extra = 3
    

@admin.register(Cosmetic)
class CosmeticAdmin(admin.ModelAdmin):
    list_display = [field.name for field in Cosmetic._meta.get_fields() if isinstance(field, Field) and not isinstance(field, ManyToManyField)]
    inlines = [ CosmeticImageInline, ]

  
@admin.register(Brand)
class BrandAdmin(admin.ModelAdmin):
    list_display = [field.name for field in Brand._meta.get_fields() if isinstance(field, Field) and not isinstance(field, ManyToManyField)]
    
@admin.register(Type)
class TypeAdmin(admin.ModelAdmin):
    list_display = [field.name for field in Type._meta.get_fields() if isinstance(field, Field) and not isinstance(field, ManyToManyField)]
    
@admin.register(Comment)
class CommentAdmin(admin.ModelAdmin):
    list_display = [field.name for field in Comment._meta.get_fields() if isinstance(field, Field) and not isinstance(field, ManyToManyField)]
    
@admin.register(CosmeticImage)
class CommentAdmin(admin.ModelAdmin):
    list_display = [field.name for field in CosmeticImage._meta.get_fields() if isinstance(field, Field) and not isinstance(field, ManyToManyField)]