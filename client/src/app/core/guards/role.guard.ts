import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { map, catchError, of } from 'rxjs';
import { AuthService } from '../services/auth.service';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const allowedRoles = route.data['roles'] as string[];

  return authService.me().pipe(
    map((user) => {
      console.log(user)
      if (user && allowedRoles.includes(user.role)) {
        return true;
      } else {
        return router.createUrlTree(['/unauthorized']);
      }
    }),
    catchError((error) => {
      console.error('Error fetching user role', error);
      return of(router.createUrlTree(['/unauthorized']));
    })
  );
};
