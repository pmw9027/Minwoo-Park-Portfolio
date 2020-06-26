from django.shortcuts import render, get_object_or_404
from .models import Cosmetic, Comment, CosmeticImage,Brand ,Type
from django.forms.models import model_to_dict
from rest_framework.views import APIView
from rest_framework.response import Response
from django.shortcuts import redirect
from rest_framework.permissions import IsAuthenticated
from django.contrib.auth.models import AnonymousUser

def home(request):
    return render(request,'home.html')

def cosmetic_list(request):
    cosmetics = Cosmetic.objects
    brands = Brand.objects.all()    
    types = Type.objects.all()

    
    if request.GET.get('query'):
        cosmetics = cosmetics.filter(name__contains=request.GET.get('query'))

    if request.GET.get('brand'):
        cosmetics = cosmetics.filter(brand_id=request.GET.get('brand'))
    
    if request.GET.get('category'):
        cosmetics = cosmetics.filter(type_id=request.GET.get('category'))


    return render(request, 'cosmetic.html', {
        'cosmetics' : cosmetics,
        'categories': types,                 
        'brands': brands,                                    
        })

class CommentAPIView(APIView):
    # permission_classes = (IsAuthenticated, )

    def post(self, request):
        
        _data = request.POST.copy()
        _data = _data.dict()
            
        _data['user'] = request.user
        _data.pop('csrfmiddlewaretoken')
        if isinstance(request.user, AnonymousUser):
            return redirect("signin")
        print(_data)
        Comment.objects.create(**_data)
        
        return redirect(f"cosmetics/{ request.POST.get('cosmetic_id') }")

class CosmeticAPIView(APIView):
    def get(self, request, cosmetic_id=None):
        try:
            cosmetic = Cosmetic.objects.select_related('brand').get(id=cosmetic_id)
        except Cosmetic.DoesNotExist:
            return render(request,'404.html', _data)        
        
        _data = {}
        _data['cosmetic'] = cosmetic
        _data['brand'] = cosmetic.brand.name
        _data['images'] = [image.image.url for image in cosmetic.images.all()]

        print(cosmetic.thumbnail)
        
        comments = Comment.objects.select_related('user').filter(cosmetic=cosmetic).order_by('-created_at')
        _data['comments'] = comments
        
        return render(request,'comments.html', _data)
                                    
        
        