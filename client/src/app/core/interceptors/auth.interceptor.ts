import { HttpInterceptorFn } from '@angular/common/http';
import { HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (
  request: HttpRequest<any>,
  next: HttpHandlerFn
): Observable<HttpEvent<any>> => {
  if (request.url.includes('login')) {
    return next(request);
  }

  const accessToken = localStorage.getItem('access_token');

  const authRequest = request.clone({
    setHeaders: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return next(authRequest);
};
