    package org.example.backend.security;

    import org.example.backend.enums.UserStatus;
    import org.example.backend.organization.User.User;
    import org.example.backend.organization.User.UserRepository;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        public CustomUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {

            User user = userRepository.findByEmailAndStatus(username, UserStatus.active)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "User not found with email: " + username
                            )
                    );

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        }
    }